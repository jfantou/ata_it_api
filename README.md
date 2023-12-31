Important: it's an API created with java 17

# JOB DATA API

You will find some informations to use the API.

## Launch API  

This API is an spring boot application using MAVEN, you can use a maven command line to launch it:
mvn spring-boot:run

Or if you use an IDE like intellij you can launch directly on it.

### Docker

You can also use docker if you want:
- Build your image with this command: docker build -t api
- Run the image: docker run -it -p 8080:8080 api:latest
- Access to the applciation via the localhost URL on port 8080: http://localhost:8080/swagger-ui/index.html

## SWAGGER

You can find the swagger interface at this URL: http://localhost:8080/swagger-ui/index.html.
You can test the endpoint with this interface the API or by POSTMAN.

### Data

SalaryInformation:

- Date timestamp;
- String employer;
- String location;
- String job_title;
- float years_at_employer;
- float years_of_experience;
- integer salary;
- double signing_bonus;
- double annual_bonus;
- double annual_stock_value_bonus;

## Filter option

If you want to filter the data you will need to add the parameter variable "filters" in the URL.
The parameter variable accept only three colums: job_title, salary and gender. 
If you put an other variable value, the API will return an error response with a code status at 400.

Example: filters=salary>1000

### Operation accepted
For filter by gender or job_title, you need to use the symbol "=" and put the value.
Example: gender=M (You will have only results with a gender equals to "M")

For filter by salary you will need to put mathematics symbols: 
- ">="
- ">"
- "<="
- "<"
- "="

Example: filters=salary>=1000

Filter multiple colunm is possible also.

Example: filters=salary>=1000,job_title=Software Engineer

## Sort option

You can also sort the data if you put a parameter variable "sort".

Example: sort=location

Example multiple field: sort=location,employer

By default the sort is doing on the natural order. But you can decide to reverse the order by putting a parameter variable "sort_type".
Example: "sort_type"=DESC
