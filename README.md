# 🎓 EduMatrix - Advanced Learning Management System

A modern, feature-rich Learning Management System built with Spring Boot, featuring course management, student activity tracking, performance analytics, and dark mode support.

## ✨ Key Features

### 📚 Course Management
- Create, edit, and delete courses
- Organize content into modules
- Upload videos and documents
- Publish/unpublish courses
- Category-based organization

### 👥 User Management
- Role-based access (Student, Instructor, Admin)
- User registration and authentication
- Profile management
- Secure password encryption

### 📊 Activity Tracking
- Real-time activity monitoring
- Login/logout tracking
- Course access logs
- Time spent analytics
- Module view tracking

### 📈 Performance Analytics
- Student performance metrics
- Course completion rates
- Grade analytics
- Time spent analysis
- Comparative analytics
- Visual dashboards with charts

### 📄 Learning Reports
- Individual student reports
- Course performance reports
- Instructor analytics
- Export capabilities (PDF/Excel ready)
- Custom date ranges

### 🌙 Dark Mode
- Toggle between light and dark themes
- Persistent theme preference
- Smooth transitions
- Eye-friendly design

## 🛠️ Technology Stack

- **Backend**: Java 21, Spring Boot 3.2.5
- **Security**: Spring Security 6
- **Database**: MySQL 8.0+
- **ORM**: Spring Data JPA / Hibernate
- **Frontend**: Thymeleaf, HTML5, CSS3, JavaScript
- **Build Tool**: Maven

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

1. **Java 21 or higher**
   - Download: https://www.oracle.com/java/technologies/downloads/#java21
   - Verify: `java -version`

2. **Maven 3.6+**
   - Download: https://maven.apache.org/download.cgi
   - Verify: `mvn -version`

3. **MySQL 8.0+**
   - Download: https://dev.mysql.com/downloads/installer/
   - Remember your root password during installation

4. **Git** (optional)
   - Download: https://git-scm.com/downloads

## 🚀 Installation & Setup

### Step 1: Install MySQL

1. Download and install MySQL from the link above
2. During installation, set a root password (remember this!)
3. Start MySQL service

### Step 2: Create Database

Open MySQL Command Line or MySQL Workbench and run:

```sql
CREATE DATABASE edumatrix;
```

Or simply run the provided schema file:

```bash
mysql -u root -p < schema.sql
```

### Step 3: Configure Application

1. Open `src/main/resources/application.properties`
2. Update the MySQL password:

```properties
spring.datasource.password=your_mysql_password
```

Replace `your_mysql_password` with your actual MySQL root password.

### Step 4: Build the Project

```bash
mvn clean install
```

### Step 5: Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/edumatrix-1.0.0.jar
```

### Step 6: Access the Application

Open your browser and navigate to:

- **Application**: http://localhost:8080
- **Login**: http://localhost:8080/login
- **Register**: http://localhost:8080/register

## 👤 First Time Setup

1. Go to http://localhost:8080/register
2. Create your first account:
   - **Full Name**: Your name
   - **Username**: Choose a username
   - **Email**: Your email
   - **Password**: Choose a password
   - **Role**: Select STUDENT, INSTRUCTOR, or ADMIN

3. Login with your credentials
4. Start exploring!

## 📁 Project Structure

```
EduMatrix/
├── src/main/java/com/edumatrix/
│   ├── controller/          # REST controllers
│   │   ├── AuthController.java
│   │   ├── CourseController.java
│   │   └── AnalyticsController.java
│   ├── model/              # JPA entities
│   │   ├── User.java
│   │   ├── Course.java
│   │   ├── Module.java
│   │   ├── Enrollment.java
│   │   ├── Activity.java
│   │   └── Performance.java
│   ├── repository/         # Data access layer
│   ├── service/            # Business logic
│   │   ├── UserService.java
│   │   ├── CourseService.java
│   │   ├── ActivityTrackingService.java
│   │   └── AnalyticsService.java
│   ├── security/           # Security configuration
│   └── dto/                # Data transfer objects
├── src/main/resources/
│   ├── static/
│   │   ├── css/           # Separated CSS files
│   │   │   ├── main.css
│   │   │   ├── dashboard.css
│   │   │   ├── course.css
│   │   │   ├── analytics.css
│   │   │   └── responsive.css
│   │   └── js/            # JavaScript files
│   │       ├── theme-toggle.js
│   │       └── activity-tracker.js
│   ├── templates/         # Thymeleaf templates
│   │   ├── auth/
│   │   ├── dashboard/
│   │   ├── course/
│   │   └── analytics/
│   └── application.properties
├── schema.sql             # Database schema
├── pom.xml               # Maven configuration
└── README.md
```

## 🎯 Core Modules

### 1. Course Management
- **Endpoint**: `/courses`
- **Features**: CRUD operations, module management, content upload

### 2. User Management
- **Endpoint**: `/users`
- **Features**: Registration, authentication, role management

### 3. Activity Tracking
- **Service**: `ActivityTrackingService`
- **Tracks**: Logins, course access, time spent, module views

### 4. Analytics Dashboard
- **Endpoint**: `/analytics`
- **Features**: Performance metrics, charts, reports

## 🔒 Security Features

- BCrypt password encryption
- Role-based access control (RBAC)
- Spring Security integration
- Session management
- CSRF protection
- Secure authentication

## 🎨 Dark Mode Usage

Click the 🌙/☀️ button in the navbar to toggle between light and dark themes. Your preference is automatically saved.

## 📊 Database Schema

The application uses 6 main tables:
- **users**: User accounts and profiles
- **courses**: Course information
- **modules**: Course modules and content
- **enrollments**: Student-course relationships
- **activities**: Activity tracking logs
- **performance**: Performance metrics

## 🧪 Testing

Run tests with:

```bash
mvn test
```

## 🐛 Troubleshooting

### Port 8080 already in use
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -ti:8080 | xargs kill -9
```

### Database connection error
- Verify MySQL is running
- Check username/password in application.properties
- Ensure database 'edumatrix' exists

### Build failures
```bash
mvn clean install -U
```

## 📝 Configuration

### Change Port
In `application.properties`:
```properties
server.port=9090
```

### Change Database
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 🚀 Deployment

### Production Build
```bash
mvn clean package -DskipTests
java -jar target/edumatrix-1.0.0.jar
```

### Environment Variables
```bash
export DB_PASSWORD=your_password
export SERVER_PORT=8080
```

## 📈 Future Enhancements

- [ ] Real-time notifications
- [ ] Video conferencing integration
- [ ] Mobile application
- [ ] AI-powered recommendations
- [ ] Advanced reporting (PDF/Excel export)
- [ ] Multi-language support
- [ ] Discussion forums
- [ ] Assignment submissions

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For issues and questions:
- Create an issue on GitHub
- Email: support@edumatrix.com

## 👨‍💻 Author

Built with ❤️ for modern education

---

**Happy Learning! 🎓**
