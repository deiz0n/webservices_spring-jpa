
    create table tb_category (
        id uuid not null,
        name varchar(255),
        primary key (id)
    );

    create table tb_order (
        order_status smallint check (order_status between 0 and 4),
        moment timestamp(6) with time zone,
        id uuid not null,
        user_id uuid,
        primary key (id)
    );

    create table tb_order_item (
        price float(53),
        quantity integer,
        order_id uuid not null,
        product_id uuid not null,
        primary key (order_id, product_id)
    );

    create table tb_payment (
        moment timestamp(6) with time zone,
        order_id uuid not null,
        primary key (order_id)
    );

    create table tb_product (
        price float(53),
        id uuid not null,
        description varchar(255),
        imgurl varchar(255),
        name varchar(255) unique,
        primary key (id)
    );

    create table tb_product_category (
        category_id uuid not null,
        product_id uuid not null,
        primary key (category_id, product_id)
    );

    create table tb_user (
        id uuid not null,
        address varchar(255),
        email varchar(255) unique,
        name varchar(255),
        password varchar(255),
        phone varchar(255) unique,
        primary key (id)
    );

    alter table if exists tb_order 
       add constraint FK2p4n9ciui39792tk5qdpcxq1w 
       foreign key (user_id) 
       references tb_user;

    alter table if exists tb_order_item 
       add constraint FK4h5xid5qehset7qwe5l9c997x 
       foreign key (product_id) 
       references tb_product;

    alter table if exists tb_order_item 
       add constraint FKgeobgl2xu916he8vhljktwxnx 
       foreign key (order_id) 
       references tb_order;

    alter table if exists tb_payment 
       add constraint FKokaf4il2cwit4h780c25dv04r 
       foreign key (order_id) 
       references tb_order;

    alter table if exists tb_product_category 
       add constraint FK5r4sbavb4nkd9xpl0f095qs2a 
       foreign key (category_id) 
       references tb_category;

    alter table if exists tb_product_category 
       add constraint FKgbof0jclmaf8wn2alsoexxq3u 
       foreign key (product_id) 
       references tb_product;
