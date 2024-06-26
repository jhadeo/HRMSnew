select * from RoomReservation
select * from Room
select * from ConferenceRooms
select* from HotelEvents
select* from Guest

select concat(Guest.FirstName, ' ',Guest.LastName) as [GuestName], RoomReservation.RoomNo,RoomReservation.CheckInDate,RoomReservation.CheckOutDate,RoomReservation.CheckOutDate, RoomReservation.RoomRate, RoomReservation.Taxes,RoomReservation.Total, RoomReservation.PaymentStatus, RoomReservation.PayMethod from RoomReservation join Guest on RoomReservation.GuestID = Guest.GuestID

select * from HotelEvents

select * from EventReservation

select concat(Guest.FirstName, ' ', Guest.LastName) as [ReservedTo], HotelEvents.EventName, ConferenceRooms.ConfRoomNo, EventReservation.EventDate,EventReservation.Duration, EventReservation.ConfRoomCharge, EventReservation.CateringCharge, EventReservation.RequestCharge, EventReservation.Taxes, EventReservation.Total, EventReservation.PaymentStatus, EventReservation.PayMethod from EventReservation join Guest on EventReservation.GuestID = Guest.GuestID join ConferenceRooms on EventReservation.RoomAssigned = ConferenceRooms.ConfRoomNo join HotelEvents on HotelEvents.EventID = EventReservation.EventID

select * from ConferenceRooms

select concat(Guest.FirstName, ' ',Guest.LastName) as[Name],RoomReservation.CheckOutDate, RoomReservation.Total, RoomReservation.PaymentStatus from RoomReservation join Guest on Guest.GuestID = RoomReservation.GuestID join Room on Room.RoomNo = RoomReservation.RoomNo where RoomStatus = 'Reserved' and CheckOutDate >= GETDATE() and RoomReservation.RoomNo = 101

INSERT INTO RoomReservation (GuestID, RoomNo, CheckInDate, CheckOutDate, RoomRate, Taxes, Total, PaymentStatus, PayMethod, CheckOutStatus)
VALUES
(1, 101, '2024-04-15', '2024-04-16', 100, 12, 112, 0, 'Cash', 0),
(2, 102, '2024-09-11', '2024-09-17', 150, 18, 168, 1, 'Bank Transfer',0),
(3, 103, '2024-10-16', '2024-10-18', 250, 30, 280, 0, 'Cheque',0),
(4, 104, '2024-04-17', '2024-04-19', 100, 12, 112, 1, 'CashApp',1),
(5, 105, '2024-11-17', '2024-11-20', 150, 18, 168, 0, 'Cash',0);

UPDATE RoomReservation SET PaymentStatus = 1, CheckOutStatus = 1 WHERE RoomNo = 101 AND CheckOutStatus = 0 AND CheckInDate < GETDATE();

