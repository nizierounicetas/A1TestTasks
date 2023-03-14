package CsvModels;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RawCsvPosting {

    @CsvBindByName(column = "Mat. Doc.")
    private String matDoc;

    @CsvBindByName(column = "Item")
    private String item;

    @CsvBindByName(column = "Doc. Date")
    private String docDate;

    @CsvBindByName(column = "Pstng Date")
    private String postingDate;

    @CsvBindByName(column = "Material Description")
    private String materialDescription;

    @CsvBindByName(column = "Quantity")
    private String quantity;

    @CsvBindByName(column = "BUn")
    private String bun;

    @CsvBindByName(column = "Amount LC")
    private String amountLc;

    @CsvBindByName(column = "Crcy")
    private String currency;

    @CsvBindByName(column = "User Name")
    private String userName;

    public String getMatDoc() {
        return matDoc;
    }

    public String getItem() {
        return item;
    }

    public String getDocDate() {
        return docDate;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getBun() {
        return bun;
    }

    public String getAmountLc() {
        return amountLc;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUserName() {
        return userName;
    }

    public void setMatDoc(String matDoc) {
        this.matDoc = matDoc;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public void setAmountLc(String amountLc) {
        this.amountLc = amountLc;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format("Mat. Doc.: %s; Item: %s; Doc. Date: %s; Pstng Date: %s; Material Description: %s; " +
                "Quantity: %s, BUn: %s; Amount LC: %s; Crcy: %s; User Name: %s",
                matDoc, item, docDate, postingDate, materialDescription, quantity, bun, amountLc, currency, userName);
    }
}
