CREATE TABLE tbl_shop (
	id 				BIGINT 			AUTO_INCREMENT,
	name 			VARCHAR(255) 	NOT NULL
);

CREATE TABLE tbl_product (
	id 				BIGINT 			AUTO_INCREMENT,
	name 			VARCHAR(255) 	NOT NULL,
	dollar_value	DECIMAL(19,2)	NOT NULL,
	shop_fk			BIGINT			NOT NULL
);

ALTER TABLE tbl_product ADD FOREIGN KEY (shop_fk) REFERENCES tbl_shop(id);

CREATE TABLE tbl_order (
	id 				BIGINT 			AUTO_INCREMENT,
	date_time		TIMESTAMP		NOT NULL,
	dollar_value	DECIMAL(19,2)	NOT NULL,
	status			VARCHAR(255) 	NOT NULL
);

CREATE TABLE tbl_line_item (
	id 				BIGINT 			AUTO_INCREMENT,
	order_fk		BIGINT			NOT NULL,
	product_fk		BIGINT			NOT NULL,	
	amount			INT				NOT NULL
);

ALTER TABLE tbl_line_item ADD FOREIGN KEY (order_fk) REFERENCES tbl_order(id);
ALTER TABLE tbl_line_item ADD FOREIGN KEY (product_fk) REFERENCES tbl_product(id);