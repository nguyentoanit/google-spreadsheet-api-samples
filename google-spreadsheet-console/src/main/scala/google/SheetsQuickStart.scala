package google

import java.io.{File, FileNotFoundException, IOException, InputStreamReader}
import java.util.Collections

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.{GoogleAuthorizationCodeFlow, GoogleClientSecrets}
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.SheetsScopes


// Ref: https://developers.google.com/sheets/api/quickstart/java
trait SheetsQuickstart {
  val APPLICATION_NAME = "Google Sheets API Scala Quick start"
  val JSON_FACTORY = JacksonFactory.getDefaultInstance
  val TOKENS_DIRECTORY_PATH = "tokens"
  /**
    * Global instance of the scopes required by this quickstart.
    * If modifying these scopes, delete your previously saved tokens/ folder.
    */
  private val SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)

  // Get credential file on https://developers.google.com/sheets/api/quickstart/java
  private val CREDENTIALS_FILE_PATH = "/credentials.json"

  /**
    * Creates an authorized Credential object.
    *
    * @param HTTP_TRANSPORT The network HTTP Transport.
    * @return An authorized Credential object.
    * @throws IOException If the credentials.json file cannot be found.
    */
  @throws[IOException]
  def getCredentials(HTTP_TRANSPORT: NetHttpTransport, scope: java.util.Collection[String] = SCOPES): Credential = { // Load client secrets.
    val in = classOf[SheetsQuickstart].getResourceAsStream(CREDENTIALS_FILE_PATH)

    if (in == null) throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH)
    val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in))
    // Build flow and trigger user authorization request.
    val flow: GoogleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scope).setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH))).setAccessType("offline").build
    val receiver: LocalServerReceiver = new LocalServerReceiver.Builder().setPort(8888).build
    new AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
  }
}
