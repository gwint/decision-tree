package server;

import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;
import org.json.JSONObject;

public class RequestHandler implements Runnable {
  private Socket clientConnection;

  public RequestHandler(Socket clientConnectionIn) {
    if(clientConnectionIn == null) {
      throw new IllegalArgumentException("Client connection socket must not be null");
    }
    this.clientConnection = clientConnectionIn;
  }
  public void run() {
    System.out.println("Now handling classifier request...");
    // classify using decision tree classifier
    // return json response to client
    InputStream datasetStream = null;
    try {
      datasetStream = this.clientConnection.getInputStream();
      String datasetString = "";

      int byteRead = datasetStream.read();
      while(byteRead > -1) {
        datasetString =
              datasetString.concat(new String(new byte[] {(byte) byteRead}));
        byteRead = datasetStream.read();
      }

      System.out.println(datasetString);

      JSONObject jsonObj = new JSONObject(datasetString);

      // Check that numFeatures is provided
      // Check that at least one test sample index is provided
      // Check that number of classes matches number of attributes provided

      try {
        this.clientConnection.close();
      }
      catch(IOException e) {
        System.err.println(e.getMessage());
        System.exit(1);
      }
      finally {}
    }
    catch(IOException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
    finally {}
  }
}
