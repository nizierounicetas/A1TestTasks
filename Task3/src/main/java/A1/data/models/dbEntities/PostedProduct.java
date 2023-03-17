package A1.data.models.dbEntities;

import javax.persistence.*;

@Entity
@Table(name = "Posting_Product")
public class PostedProduct {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_number")
    private Posting posting;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private int item;

    private int quantity;
    private String currency;
    private String unit;

    @Column(name = "amount_lc")
    private Double amountLc;

    public Long getId() {
        return id;
    }
    public Posting getPosting() {
        return posting;
    }

    public Double getAmountLc() {
        return amountLc;
    }

    public Product getProduct() {
        return product;
    }

    public int getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setAmountLc(Double amountLc) {
        this.amountLc = amountLc;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

