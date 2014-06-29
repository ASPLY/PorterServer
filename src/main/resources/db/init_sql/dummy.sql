INSERT INTO `users` (`ID`, `NAME`, `NAME_CRC`, `EMAIL`, `EMAIL_CRC`, `PASSWORD`, `TELEPHONE`)
VALUES
	(1, 'chan', -1728115760, 'kd980311@naver.com', -1132856538, 'd690d1828079f0bc9ad74ed9712ff328f845e50eae82205172ca4359bfe7922c74742013e2b26e60', '010-5215-3486');

INSERT INTO `users_authorities` (`USER_ID`, `AUTHORITY`)
VALUES
	(1, 'ROLE_USER');

	
INSERT INTO `products` (`PRODUCT_ID`, `USER_ID`, `CAR_MAKER_NO`, `CAR_TYPE_NO`, `CAR_MODEL_NO`, `CAR_YEAR`, `MAIN_CATEGORY_NO`, `SUB_CATEGORY_NO`, `NAME`, `PRICE`, `QUANTITY`, `LIST_IMAGE_URL`)
VALUES
	(1, 1, 1000, 2000, 3000, 1991, 5000, 6000, '짱좋은 바퀴', 150000, 3, 'http://sply.cdn3.cafe24.com/list/5D24F48A50914FF881BB8C9C8E23282C.jpg'),
	(2, 1, 1000, 2000, 3000, 1991, 5000, 6000, '짱좋은 바퀴', 150000, 3, 'http://sply.cdn3.cafe24.com/list/737BCB577CCD44D393FDB5945B1B575A.jpg'),
	(3, 1, 1000, 3000, 4000, 1991, 5000, 6000, '짱좋은 바퀴', 150000, 3, 'http://sply.cdn3.cafe24.com/list/64ABE84D3902474498DF162FC34E85B1.jpg'),
	(4, 1, 1000, 3000, 4000, 1991, 5000, 6000, '짱좋은 바퀴', 150000, 3, 'http://sply.cdn3.cafe24.com/list/3EEEC78AE2694E71A0190C18A1EA9659.jpg'),
	(8, 1, 1000, 2000, 3000, 1991, 5000, 6000, '짱좋은 바퀴', 150000, 3, 'http://sply.cdn3.cafe24.com/list/7048EAB068BF4D35BCC2FA48FBE08C1E.jpg');

	
INSERT INTO `products_des_image_urls` (`PRODUCT_ID`, `NORMAL_URL`, `ZOOMIN_URL`)
VALUES
	(1, 'http://sply.cdn3.cafe24.com/normal/E23A9A12F5404B29AB52443378FD9B3C.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/2F1CC45AF0DB48C7B5CE61BCFF95D07F.jpg'),
	(1, 'http://sply.cdn3.cafe24.com/normal/D68C81AB73BC4011B5A047E57E2F6612.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/C586A9B0BE7C4BD59ED08A38E39965B0.jpg'),
	(2, 'http://sply.cdn3.cafe24.com/normal/E2E91C14D14C475F8C118F0EFEB6DEBD.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/34C0E1055CBB482D823D7E17BE9EC46B.jpg'),
	(2, 'http://sply.cdn3.cafe24.com/normal/7C905312E5374E4EA4F4B012A191F229.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/965552EEE5494DCCA5443A962D52F8D5.jpg'),
	(3, 'http://sply.cdn3.cafe24.com/normal/0A5D1B99F54E4083ABF2CFABB0E38920.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/A6FAF6574AF24541B8576EC5BDA8FA02.jpg'),
	(3, 'http://sply.cdn3.cafe24.com/normal/F4BFD6CACEF1467DAFEE47B8A3658428.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/BB3D6AC316DA4195A8FB97E951F78AED.jpg'),
	(4, 'http://sply.cdn3.cafe24.com/normal/BDDFDC05BD96413A95750B305FB6E413.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/75EB6D79D954406C8ED08983166DB813.jpg'),
	(4, 'http://sply.cdn3.cafe24.com/normal/049A5608A9A9426DBB9F894435B470A6.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/2056E4875988414586263E0505FC1A23.jpg'),
	(1, 'test1.jpg', 'test.jpg'),
	(8, 'http://sply.cdn3.cafe24.com/normal/18F409BD69944C778BF3F083AFEB9653.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/AB77B5F19EB442F1877E79FC099411BD.jpg'),
	(8, 'http://sply.cdn3.cafe24.com/normal/852E2965127446318E7E382CC23C39A0.jpg', 'http://sply.cdn3.cafe24.com/zoomIn/F2D6EFD3624F41228262F55FA006B18A.jpg');

	
INSERT INTO `products_state` (`PRODUCT_ID`, `STATE`)
VALUES
	(1, '상태 좋아요 ! A급 뒷면에 조금 기스가 있는거 빼구 ㄱㄱ'),
	(2, '상태 좋아요 ! A급 뒷면에 조금 기스가 있는거 빼구 ㄱㄱ'),
	(3, '상태 좋아요 ! A급 뒷면에 조금 기스가 있는거 빼구 ㄱㄱ'),
	(4, '상태 좋아요 ! A급 뒷면에 조금 기스가 있는거 빼구 ㄱㄱ'),
	(8, '상태 좋아요 ! A급 뒷면에 조금 기스가 있는거 빼구 ㄱㄱ');

	
	