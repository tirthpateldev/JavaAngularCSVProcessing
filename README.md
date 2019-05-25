# JavaAngularCSVProcessing

Step 1: Core Java
To run core java application please import the project. Put csv file in some folder and update same path under application.propertis file in processedFrom property. Once completed please run Main class. It will pick multiple file parallel but as I have used ThreadPoolExecutor with size 1 it will process only one record at a time.
Check application logs.
Booking ID: 343434 for Property: 123and amount 260 was processed SUCCESSFULLY!
Booking ID: 343435 for Property: 123and amount 260 was processed FAILED!
Booking ID: 343436 for Property: 123and amount 260 was processed SUCCESSFULLY!


Step 2: Spring
To run Spring boot application please import the project. Once completed updated csv file location inside application.yml in processedFrom. If you want to update scheduler time interval please update it on ScheduleReader->runFilePeriodically class. Once completed please run Application.java file.
Request sample:
curl -X GET \
  http://192.168.1.21:8080/bookings \
  -H 'Postman-Token: 8244e90f-10cd-4971-9b25-efb5f0d58749' \
  -H 'cache-control: no-cache'
{"data":[{"bookingId":"343434","cardNumber":"4242424242424242"},{"bookingId":"343436","cardNumber":"4242424242424242"},{"bookingId":"343435","cardNumber":"4242424242424244"}]}

curl -X DELETE \
  'http://192.168.1.21:8080/deleteBookings?username=admin&password=changeme&id=343434' \
  -H 'Postman-Token: 92fea595-6e44-48b0-b868-640565ed40cf' \
  -H 'cache-control: no-cache'
record has been deleted for now, it may added when scheduler again run

curl -X DELETE \
  'http://192.168.1.21:8080/deleteBookings?username=admin&password=changeme&id=343434' \
  -H 'Postman-Token: 92fea595-6e44-48b0-b868-640565ed40cf' \
  -H 'cache-control: no-cache'
provided booking Id does not exists

curl -X GET \
  http://192.168.1.21:8080/bookings \
  -H 'Postman-Token: 751d8db3-6da1-4150-8c1d-520c02f154a4' \
  -H 'cache-control: no-cache'
{"data":[{"bookingId":"343436","cardNumber":"4242424242424242"},{"bookingId":"343435","cardNumber":"4242424242424244"}]}  

Step 3: Angular
1. Please make sure you have Node installed with versing = 8.9.0
2. Please download code and run following command from root directory.
   npm Install
   ng serve
3. Please open below url to see data.
	http://localhost:4200/
4. As this is for demo only we are using cors-anywhere.herokuapp.com services to avoid cors issue.
