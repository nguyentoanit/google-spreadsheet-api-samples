package example

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.services.sheets.v4.Sheets

object Hello extends App with SheetsQuickstart {

  val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport
  val spreadsheetId: String = "xxx"
  val range: String = "Sheet1!A1:A11"

  val service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build
  val response = service.spreadsheets.values.get(spreadsheetId, range).execute
  val values = response.getValues
  if (values == null || values.isEmpty) System.out.println("No data found.")
  else {
    import scala.jdk.CollectionConverters._
    for(cell <- values.asScala) {
      cell.asScala.foreach(println)
    }
  }
}
