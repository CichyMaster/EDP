package Model;

public class TableContents {

    static Service service = Service.getInstance();
    public static final Object[] TABLE_HEADER = {"Nr Przypadku", "IMEI",
    "Model", "Technik", "Operator", "Status Naprawy", "Data Przyjęcia", "Data Zakończenia"};

    public static Object[][] contents(){
        Object[][] cases = new Object[service.selectRepairs().size()][8];
        for(int i = 0; i<service.selectRepairs().size(); i++){
            cases[i][0] = service.selectRepairs().get(i).getNrCase();
            cases[i][1] = service.selectRepairs().get(i).getImei();
            cases[i][2] = service.selectRepairs().get(i).getModel();
            cases[i][3] = service.selectRepairs().get(i).getAcronym();
            cases[i][4] = service.selectRepairs().get(i).getOperator();
            cases[i][5] = service.selectRepairs().get(i).getStatus();
            cases[i][6] = service.selectRepairs().get(i).getAdmissionDate();
            cases[i][7] = service.selectRepairs().get(i).getEndDate();
        }
        return cases;
    }

}
