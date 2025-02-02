package com.sh.onezip.product.entity;

import com.sh.onezip.businessproduct.entity.Businessmember;
//import com.sh.onezip.cart.entity.Cart;
import com.sh.onezip.productReview.entity.ProductReview;
import com.sh.onezip.productoption.entity.ProductOption;
import com.sh.onezip.productquestion.entity.ProductQuestion;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@Slf4j
@Builder
@Table(name = "tb_product")
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@ToString(exclude = {"productImages", "productOptions", "productReviews"})
@ToString(exclude = {"productOptions", "productReviews"})

public class Product implements Comparable<Product> {
    @Id
    @GeneratedValue(generator = "seq_tb_product_id_generator")
    @SequenceGenerator(
            name = "seq_tb_product_id_generator",
            sequenceName = "seq_tb_product_id",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column
    private String productName;
    @ManyToOne(fetch = FetchType.LAZY) // 02-17: EAGER->LAZY
    @JoinColumn(name = "biz_member_Id")
    private Businessmember businessmember;
    @Column
    @Enumerated(EnumType.STRING)
    private ProductType productTypecode;
    @Column
    private int productPrice;
    @Column
    private int discountRate;
    @Column
    @CreationTimestamp
    private LocalDate regDate;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)  // 02-23 EAGER->LAZY
//    @Builder.Default
//    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY) // 02-23 EAGER->LAZY
    @Builder.Default
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY) // 02-23 EAGER->LAZY
    @Builder.Default
    private List<ProductQuestion> productQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY) // 02-23 EAGER->LAZY
    @Builder.Default
    private List<ProductReview> productReviews = new ArrayList<>();

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY) // 02-23 EAGER->LAZY
//    @Builder.Default
//    private List<Cart> cart = new ArrayList<>();

    @Override
    public int compareTo(Product other) {
        return (int)(this.id - other.id);
    }

}