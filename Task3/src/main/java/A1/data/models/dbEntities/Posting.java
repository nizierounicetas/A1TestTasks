package A1.data.models.dbEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Posting.selectAll", query = "SELECT p FROM Posting p"),
        @NamedQuery(name = "Posting.selectByIsAuthorized", query = "SELECT p FROM Posting p WHERE p.isAuthorized = " +
                ":isAuthorized"),
        @NamedQuery(name = "Posting.selectByPeriod", query = "SELECT p FROM Posting p WHERE p.postingDate " +
                "BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = "Posting.selectByPeriodAndIsAuthorized", query = "SELECT p FROM Posting p WHERE " +
                "p.isAuthorized = :isAuthorized AND p.postingDate BETWEEN :startDate AND :endDate")
})
public class Posting {
    @Id
    private Long number;

    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @Column(name = "posting_date")
    @Temporal(TemporalType.DATE)
    private Date postingDate;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "is_authorized")
    private Boolean isAuthorized;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posting", cascade = CascadeType.ALL)
    List<PostedProduct> postedProducts = new ArrayList<>();

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setIsAuthorized(Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setPostedProducts(List<PostedProduct> postedProducts) {
        this.postedProducts = postedProducts;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public Date getDocDate() {
        return docDate;
    }

    public Long getNumber() {
        return number;
    }

    public String getAccountName() {
        return accountName;
    }

    public List<PostedProduct> getPostedProducts() {
        return postedProducts;
    }

    public Boolean getIsAuthorized() {
        return isAuthorized;
    }
}
