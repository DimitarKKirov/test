

Create table if not exists Items (ItemID int unsigned not null auto_increment,ItemSerialNumber int not null default 00000000,ItemName varchar(50) not null unique, primary key (ItemID));
Create table if not exists ItemsDetails(ItemID int unsigned not null default 1 ,DetailsID int unsigned auto_increment ,ItemQuantity int not null default 0,ItemSellingPrice decimal not null default 0.0,primary key (DetailsID),foreign key (ItemID) references Items(ItemID));
Create table if not exists ItemsLoadingDetails(LoadingDetailsID int unsigned not null auto_increment,DetailsID int unsigned not null default 1 ,ItemPaidPricePerPiece decimal not null default 0.0,storeLoadingDate datetime not null default (now()) ,primary key (LoadingDetailsID),foreign key (DetailsID) references ItemsDetails(DetailsID));
Insert ignore into Items (ItemSerialNumber, ItemName) values ("0123687954","Item1"),("0123687955","Item2"),("0123686956","Item3"),("0123684959","Item4"),("0123637976","Item5"),("0123688001","Item6"),("0963688001","Item7"),("0123688002","Item8"),("01236880036","Item9"),("012368800359","Item10");
Insert into ItemsDetails(ItemQuantity, ItemSellingPrice,ItemID) values ("16","125.65","1"),("13","86.99","2"),("1","25.65","3"),("6","149.99","4"),("16","49.65","5"),("4","1234.65","6"),("1236","1.55","7"),("236","3.99","8"),("12","5.99","9"),("40","16.99","10");
Insert into ItemsLoadingDetails(ItemPaidPricePerPiece,DetailsID) values("99.99","1"),("50","2"),("16","3"),("109.99","4"),("35","5"),("899.99","6"),("0.55","7"),("1.99","8"),("3.50","9"),("10.99","10");
