package com.jpabook.jpashop;

import com.jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    // 개체는 Collection - Collection으로 다대다 연결이 가능하지만
    // DB의 경우 다대다가 힘들다. 그래서 1:다, 다:1로 나눠서 매핑
    // 그 중간에 놓이는 테이블을 여기서 생성해주는 것
    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();


    // =============================================
    // 같은 엔티티에 대해서 매핑시켜줌 ( 카테고리 계층관계 )
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
