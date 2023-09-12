package Model;

import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {

    public static final String DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/Phone service";

    private Connection conn;
    private Statement stat;

    private static Service singleInstance = null;

    private Service() {
        try {
            Class.forName(Service.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL, "postgres", "111");
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwrciem połączenia");
            e.printStackTrace();
        }
    }

    public static synchronized Service getInstance() {
        if (singleInstance == null)
            singleInstance = new Service();

        return singleInstance;
    }

    public String nextNrCase() {
        String nextCase;
        try {
            String currentCase;
            try (ResultSet result = stat.executeQuery("SELECT nr_case FROM \"Repairs\" ORDER BY id_repair DESC LIMIT 1")) {
                result.next();
                currentCase = result.getString("nr_case");
            }

            int number = Integer.parseInt(currentCase.substring(3));
            number++;

            nextCase = currentCase.substring(0, 3) + String.format("%06d", number);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return nextCase;
    }

    public List<Phones> selectPhones() {
        List<Phones> phones = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM \"Phones\";");
            String producer, model, designation;
            while (result.next()) {
                producer = result.getString("producer");
                model = result.getString("model");
                designation = result.getString("designation");
                phones.add(new Phones(producer, model, designation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return phones;
    }

    public List<Workers> selectWorkers() {
        List<Workers> workers = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM \"Workers\";");
            String acronym, firstName, lastName;
            while (result.next()) {
                acronym = result.getString("acronym");
                firstName = result.getString("first_name");
                lastName = result.getString("last_name");
                workers.add(new Workers(acronym, firstName, lastName));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
        return workers;
    }

    public synchronized List<Repairs> selectRepairs() {
        List<Repairs> repairs = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("""
                    SELECT nr_case, imei, model, acronym, operator, status, admission_date, end_date FROM "Repairs", "Phones", "Workers"\s
                    WHERE "Phones".id_phone="Repairs".id_phone AND "Workers".id_worker="Repairs".id_worker
                     order by nr_case;""");
            String nrCase, model, acronym, operator, status, admissionDate, endDate;
            long imei;
            while (result.next()) {
                nrCase = result.getString("nr_case");
                imei = result.getLong("imei");
                model = result.getString("model");
                acronym = result.getString("acronym");
                operator = result.getString("operator");
                status = result.getString("status");
                admissionDate = result.getString("admission_date");
                endDate = result.getString("end_date");
                repairs.add(new Repairs(nrCase, imei, model, acronym, operator, status, admissionDate, endDate));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
        return repairs;
    }

    public Integer idOfModel(String model) {
        Integer idPhone;
        try {
            PreparedStatement prepStmt = conn.prepareStatement("SELECT id_phone FROM public.\"Phones\" where model=?;");
            prepStmt.setString(1, model);
            ResultSet result = prepStmt.executeQuery();

            if (result.next()) {
                idPhone = result.getInt("id_phone");
            } else {
                idPhone = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return idPhone;
    }

    public Integer idOfAcronym(String acronym) {
        Integer idWorker;
        try {
            PreparedStatement prepStmt = conn.prepareStatement("SELECT id_worker FROM public.\"Workers\" where acronym=?;");
            prepStmt.setString(1, acronym);
            ResultSet result = prepStmt.executeQuery();

            if (result.next()) {
                idWorker = result.getInt("id_worker");
            } else {
                idWorker = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return idWorker;
    }

    public synchronized void insertRepair(int idPhone, int idWorker, String nrCase, long imei, String admissionDate, String operator, String status) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into \"Repairs\" (id_phone, id_worker, nr_case, imei, admission_date, operator, end_date, status) values (?, ?, ?, ?, ?, ?, NULL, ?);");
            prepStmt.setInt(1, idPhone);
            prepStmt.setInt(2, idWorker);
            prepStmt.setString(3, nrCase);
            prepStmt.setLong(4, imei);
            prepStmt.setDate(5, validateDateInput(admissionDate));
            prepStmt.setString(6, validateStringInput(operator));
            prepStmt.setString(7, validateStringInput(status));
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Bład przy wstawianiu naprawy");
            e.printStackTrace();
        }
    }

    public synchronized void updateRepair(String nrCase, long imei, int idPhone, int idWorker, String operator, String status, String admissionDate, String endDate) {
        try {
            checkDates(admissionDate, endDate);
            PreparedStatement prepStmt = conn.prepareStatement(
                    "UPDATE \"Repairs\" SET imei=?, id_phone=?, id_worker=?, operator=?, status=?, admission_date=?, end_date=? WHERE nr_case = ?;");
            prepStmt.setLong(1, imei);
            prepStmt.setInt(2, idPhone);
            prepStmt.setInt(3, idWorker);
            prepStmt.setString(4, validateStringInput(operator));
            prepStmt.setString(5, validateStringInput(status));
            prepStmt.setDate(6, validateDateInput(admissionDate));
            prepStmt.setDate(7, validateDateInput(endDate));
            prepStmt.setString(8, nrCase.toUpperCase().trim());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException ee) {
            throw new IllegalArgumentException("Zły format");
        }
    }

    private Date validateDateInput(String input) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(input);

        if (input.equals("")) {
            return null;
        } else if (matcher.matches()) {
            return Date.valueOf(input);
        } else {
            throw new IllegalArgumentException("Zły format dat");
        }
    }

    public String validateStringInput(String input) {
        if (input.isEmpty()) {
            return null;
        } else {
            return input;
        }
    }

    public void checkDates(String earlierDate, String laterDate) {
        if (LocalDate.parse(earlierDate).isAfter(LocalDate.parse(laterDate))) {
            throw new DateTimeException("Data przyjęcia jest po dacie zakonczenia");
        }
    }

    public synchronized void deleteRepair(String nrCase) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM \"Repairs\" WHERE nr_case = ?;");
            prepStmt.setString(1, nrCase);
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

