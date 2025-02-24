# Consultation Service

## Model Design Choices
- ConsultationType: A collection of questions needed for the prescription of a product. This ensures questions can be reused across consultations and updated universally.
- Consultation: A consultation of a certain type. This will become associated with a customer upon checkout.
- Question: Questions have a enum type based on the data type of the response.
- Answer: TODO - answer will be a Java interface with different typed data fields. The underlying db will have a column per data type, with each row populating only on of the data columns.

## API Design Choices
The front end will call the API like this:
1. get consultation types for the customer to select from: GET /api/consultationtypes/
2. upon starting a consultation, create a new consultation of the correct type: POST /api/consultation {consultationType: <consultationtype uuid>}
3. for question in the returned ConsultationDto, create/update with the repsonse: PUT /api/consultations/<consultation uuid>/consutationquestion/<question uuid>
4. upon completion, get the status of the consultation: GET /api/consultation/{uuid}/status

Some design choices I made:
- Create the consultation and update with the answers separately. This has the advantage of:
  - Handling invalid question responses per question. Nothing worse than filling a form out and only finding out your responses are wrong at the end...
  - Allows the consultations to grow in length without worrying about long-lived requests server side
- Because a consultation can be created and not completed, Consultations have a status. An asynchronous cron would need to clean the database of entries that never move from CREATED.


## ToDos
There is a lot more to do...
### Missing Endpoint
I ran out of time to implement the final endpoint for getting a consultation status.
The controller would call the ConsultationService, which would do the following:
- retrieve the consultation with the called id from the repository
- call a class PrescriptionService - this would be responsible for holding all business logic relating to the initial prescription decision.
- update the consultation's status according to the decision
- return the status to the client
### Testing
I have done some basic manual integration testing with Postman, but in the real world I would write a suite of Java unit and integration tests.
### Data Validation
At the moment, I am not enforcing validation of the responses based on the question type. I would do validation something like this:
- All resposes come in as json blobs
- The Answer data field would be of type Data not string
- Data would be an abstract class encapsulating the data types with a contract for data validation -> StringData, OptionData etc would inherit
- In the transformation of the dto, I would perform validation via a Data.validate() method.
- In the repository layer I would handle writing the data to the correct table based on the class of the Data field.

