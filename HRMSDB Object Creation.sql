--create Database HRSDB
--use HRSDB
--Execute first to be safe

CREATE TABLE Room(
RoomNo INT CONSTRAINT RoomPK PRIMARY KEY,
RoomType VARCHAR(10) NOT NULL,
RoomRate MONEY NOT NULL,
RoomLimit INT NOT NULL,
RoomStatus VARCHAR(14) DEFAULT 'Available',
RoomImage VARBINARY(MAX),
CONSTRAINT RoomStatusCheck Check (RoomStatus in ('Available', 'Occupied', 'Reserved', 'In Maintenance'))
)

CREATE TABLE Guest(
GuestID INT CONSTRAINT GuestPK PRIMARY KEY IDENTITY (1,1),
FirstName VARCHAR(20) NOT NULL,
MiddleName VARCHAR(20),
LastName VARCHAR(20) NOT NULL,
PhoneNo VARCHAR(11) NOT NULL,
Email VARCHAR(345) NOT NULL,
--based on longest active email address
HomeAddress VARCHAR(60),
BirthDate DATE,
Gender VARCHAR(32),
MemberStatus INT DEFAULT 0
)

CREATE TABLE RoomReservation(
ReservationID INT,
GuestID INT NOT NULL,
RoomNo INT NOT NULL,
CheckInDate DATETIME NOT NULL,
CheckOutDate DATETIME NOT NULL,
RoomRate MONEY NOT NULL,
--Derived from Room(RoomRate) * DATEDIFF(CheckInDate, CheckOutDate) - membership or birthday discounts
Taxes MONEY NOT NULL,
--Derived from RoomRate * 12%
MiscCharges MONEY,
Total MONEY NOT NULL,
--Derived from RoomRate + Taxes + MiscCharges
PaymentStatus INT DEFAULT 0,
PayMethod VARCHAR(16),
CheckOutStatus INT DEFAULT 0,
CONSTRAINT RoomReservationPK PRIMARY KEY(ReservationID),
CONSTRAINT RoomReservationFK FOREIGN KEY (GuestID) REFERENCES Guest(GuestID),
CONSTRAINT RoomReservationFK2 FOREIGN KEY (RoomNo) REFERENCES Room(RoomNo),
CONSTRAINT RoomPaymentCheck CHECK (PaymentStatus=0 OR PaymentStatus=1),
--simulates boolean value for payment status
CONSTRAINT CheckOutStatus CHECK (CheckOutStatus=0 OR CheckOutStatus=1),
--simulates boolean value for payment status
CONSTRAINT RoomPayMethodCheck CHECK (PayMethod in ('Cash','Bank Transfer','Cheque','CashApp','Card'))
)

CREATE TABLE HotelEvents(
EventID INT CONSTRAINT HotelEventsPK PRIMARY KEY,
EventName VARCHAR(20) NOT NULL,
Catering VARCHAR(255),
AudioVisualReq VARCHAR(255),
RoomSetup VARCHAR(255),
Decorations VARCHAR(255)
)

CREATE TABLE ConferenceRooms(
ConfRoomNo INT CONSTRAINT ConferenceRoomsPK PRIMARY KEY,
Capacity INT NOT NULL,
BookRate MONEY NOT NULL,
ConfRoomStatus VARCHAR(14) DEFAULT 'Available',
ConfRoomImage VAR(MAX),
CONSTRAINT ConfRoomStatusCheck Check (ConfRoomStatus in ('Available', 'Occupied', 'Reserved', 'In Maintenance'))
)

CREATE TABLE EventReservation(
GuestID INT,
EventID INT,
RoomAssigned INT,
EventDate DATETIME,
Duration int,
--in hours
ConfRoomCharge MONEY,
--Derived from BookRate * Duration
CateringCharge MONEY,
RequestCharge MONEY,
DecorCharge MONEY,
Taxes MONEY NOT NULL,
--Derived from RoomRate * 12%
Total MONEY NOT NULL,
--Derived from RoomRate + Taxes
PaymentStatus INT DEFAULT 0,
PayMethod VARCHAR(16) NOT NULL,
CONSTRAINT EventReservationPK PRIMARY KEY(GuestID, EventID, RoomAssigned, EventDate),
CONSTRAINT EventReservationFK FOREIGN KEY (GuestID) REFERENCES Guest(GuestID),
CONSTRAINT EventReservationFK2 FOREIGN KEY (EventID) REFERENCES HotelEvents(EventID),
CONSTRAINT EventReservationFK3 FOREIGN KEY (RoomAssigned) REFERENCES ConferenceRooms(ConfRoomNo),
CONSTRAINT EventPaymentCheck CHECK (PaymentStatus=0 OR PaymentStatus=1),
CONSTRAINT EventPayMethodCheck CHECK (PayMethod in ('Cash','Bank Transfer','Cheque','CashApp'))
--simulates boolean value for payment status
)

CREATE TABLE EventRequests(
EventID INT,
Request VARCHAR(255),
CONSTRAINT EventRequestsPK PRIMARY KEY (EventID, Request),
CONSTRAINT EventRequestsFK FOREIGN KEY (EventID) REFERENCES HotelEvents(EventID)
)

CREATE TABLE STAFF (
username varchar(100) PRIMARY KEY,
staffFirstName varchar(20),
staffLastName varchar(20),
staffPassword varchar(100),
isAdmin int default 0,
CONSTRAINT isAdmin CHECK (isAdmin=0 OR isAdmin=1)
)

insert into staff values ('admin', 'password', 1)

--drop database HRSDB
