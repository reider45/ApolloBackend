package me.apollodevs.backend.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.logging.Level;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.util.DataUtil;
import me.apollodevs.backend.util.F;

import org.bukkit.scheduler.BukkitRunnable;

public class DataHandler {

	private Connection con;
	private String host, user, pass, DBName;
	private int port;

	/**
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param DBname
	 */

	public DataHandler(String host, String user, String pass, int port,
			String DBName) {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.port = port;
		this.DBName = DBName;
		DataUtil.DBName = this.DBName;

		con = createConnection();
	}

	protected Connection createConnection() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://"
					+ host + ":" + port + "/" + DBName, user, pass);
			con = connection;
			F.log("Connected to MySQL!");
			return con;
		} catch (Exception e) {
			F.log(Level.SEVERE, "==============");
			F.log(Level.SEVERE,"Could not connect to MySQL Server.");
			F.log(Level.SEVERE, "==============");
		}
		return null;
	}

	protected Boolean checkConnection() throws SQLException {
		return con != null && !con.isClosed();
	}

	protected Boolean closeConnection() throws SQLException {
		if (checkConnection()) {
			con.close();
			return true;
		}
		return false;
	}

	/**
	 * Update data into SQL
	 * @param query
	 */
	public ResultSet querySQLAsync(final String query)
			throws InterruptedException, ExecutionException {
		RunnableFuture<ResultSet> rsGet = new FutureTask<ResultSet>(
				new Callable<ResultSet>() {

					public ResultSet call() throws SQLException,
							ClassNotFoundException {
						if (!checkConnection())
							createConnection();

						return con.createStatement().executeQuery(query);
					}
				});
		new Thread(rsGet).start();
		return rsGet.get();
	}

	/**
	 * Get a result set from the database
	 * @param query
	 */
	public void updateSQLAsync(final String query) {
		new BukkitRunnable() {
			public void run() {
				try {
					if (!checkConnection()) {
						createConnection();
					}
					con.createStatement().executeUpdate(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}.runTaskAsynchronously(Apollo.getPlugin());
	}

	public Connection getConnection() {
		return con;
	}

}
