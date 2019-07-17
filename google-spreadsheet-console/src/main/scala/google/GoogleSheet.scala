package google

import java.util.{Arrays, Collection, Collections, List}

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.services.sheets.v4.model.ValueRange
import com.google.api.services.sheets.v4.{Sheets, SheetsScopes}

import scala.jdk.CollectionConverters._

object GoogleSheet extends App with SheetsQuickstart {
  val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport
  val spreadsheetId: String = "xxx"
  val sheetName: String = "Sheet1!"
  val range: String = "A1:B3"

  val scope: Collection[String] = Collections.singletonList(SheetsScopes.SPREADSHEETS)

  val service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, scope)).setApplicationName(APPLICATION_NAME).build
  val valueRange: ValueRange = new ValueRange

  val cellValues: List[List[AnyRef]] = Arrays.asList(
    Arrays.asList(
      "A1", "B1"
    ), Arrays.asList(
      "A2", "B2"
    ), Arrays.asList(
      "A3", "B3"
    )
  )

  valueRange.setValues(cellValues)
  val request = service.spreadsheets.values.update(spreadsheetId, sheetName + range, valueRange)
  request.setValueInputOption("USER_ENTERED")
  request.execute()

  val response = service.spreadsheets.values.get(spreadsheetId, sheetName + range).execute
  val values = response.getValues

  if (values == null || values.isEmpty) System.out.println("No data found.")
  else for (cell <- values.asScala) println(cell.asScala.mkString(", "))

}
