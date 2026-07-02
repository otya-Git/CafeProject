package dao;

import java.sql.Connection;
<<<<<<< HEAD
import java.sql.DriverManager;

=======

import javax.naming.InitialContext;
import javax.sql.DataSource;

// DAOの共通クラス
// このクラスは「データベース接続」を担当する
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
public class DAO {

<<<<<<< HEAD
    private static final String URL =
            "jdbc:postgresql://localhost:5432/cafe";

    private static final String USER = "postgres";

    private static final String PASSWORD = "post";

    public Connection getConnection() throws Exception {

        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
=======
    // データソース（DB接続情報）
    // staticにすることで、全DAOクラスで共有できる
    static DataSource ds;

    // データベース接続
    public Connection getConnection() throws Exception {

        // まだデータソースを取得していない場合
        if (ds == null) {

        	// ① Tomcatの設定(context.xml)を取得
            InitialContext ic = new InitialContext();

            // ② データ取得
            // jdbc/book という名前のDB設定を取得
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/book");
        }

        // データソースからDB接続を取得して返す//
        return ds.getConnection();
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    }
}