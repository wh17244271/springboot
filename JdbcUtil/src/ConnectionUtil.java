import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionUtil {
    private static String DRIVER = "com.mysql.jdbc.Driver";

    private static String URL = "jdbc:mysql://localhost:3306/venus?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";

    private static String USERNAME = "userid";

    private static String PASSWORD = "userid";

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    private CallableStatement callableStatement = null;

    private ResultSet resultSet = null;


    public ConnectionUtil(){

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误");
            System.out.println(e.getMessage());
        }
    }



    public Connection getConnection(){
        try{
            conn = DriverManager.getConnection(URL, USERNAME,
                    PASSWORD);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public int executeUpdate(String sql, Object[] params){
        int affectedLine = 0;

        try{
            conn = this.getConnection();

            pstmt = conn.prepareStatement(sql);

            if (params != null){
                for (int i = 0; i < params.length; i++){
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            affectedLine = pstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            closeAll();
        }

        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     */
    private ResultSet executeQueryRS(String sql, Object[] params){
        try{
            conn = this.getConnection();

            pstmt = conn.prepareStatement(sql);

            if (params != null){
                for (int i = 0; i < params.length; i++){
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            resultSet = pstmt.executeQuery();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultSet;
    }

    /**
     * 获取结果集，并将结果放在List中
     */
    public List<Object> excuteQuery(String sql, Object[] params){
        ResultSet rs = executeQueryRS(sql, params);

        ResultSetMetaData rsmd = null;

        int columnCount = 0;
        try{
            rsmd = rs.getMetaData();

            columnCount = rsmd.getColumnCount();
        }catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        List<Object> list = new ArrayList<Object>();

        try{
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            closeAll();
        }

        return list;
    }

    /**
     * 存储过程带有一个输出参数的方法
     * @param sql 存储过程语句
     * @param params 参数数组
     * @param outParamPos 输出参数位置
     * @param SqlType 输出参数类型
     * @return 输出参数的值
     */
    public Object excuteQuery(String sql, Object[] params,int outParamPos, int SqlType){
        Object object = null;
        conn = this.getConnection();

        try{
            callableStatement = conn.prepareCall(sql);

            if(params != null){
                for(int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }
            }

            callableStatement.registerOutParameter(outParamPos, SqlType);

            callableStatement.execute();

            object = callableStatement.getObject(outParamPos);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            closeAll();
        }

        return object;
    }

    private void closeAll(){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

        if(pstmt != null){
            try{
                pstmt.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

        if(callableStatement != null){
            try{
                callableStatement.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if(conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ConnectionUtil conn = new ConnectionUtil();
        String sql = "SELECT * FROM DA_CONS_GATHER_DATA LIMIT 1 ";
        List<Object> objects = conn.excuteQuery(sql, null);
        System.out.println("菜鸟裹裹");
    }
}
