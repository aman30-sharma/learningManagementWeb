# 🚀 EduMatrix Setup Guide

## Step-by-Step Installation

### 1️⃣ Install Java 21

1. Go to: https://www.oracle.com/java/technologies/downloads/#java21
2. Download **Java 21** for Windows
3. Run the installer
4. Verify installation:
   ```cmd
   java -version
   ```
   Should show: `java version "21.x.x"`

### 2️⃣ Install Maven

1. Go to: https://maven.apache.org/download.cgi
2. Download **Binary zip archive**
3. Extract to `C:\Program Files\Maven`
4. Add to PATH:
   - Search "Environment Variables" in Windows
   - Edit "Path" variable
   - Add: `C:\Program Files\Maven\bin`
5. Verify:
   ```cmd
   mvn -version
   ```

### 3️⃣ Install MySQL

1. Go to: https://dev.mysql.com/downloads/installer/
2. Download **MySQL Installer**
3. Run installer and select:
   - MySQL Server
   - MySQL Workbench (optional)
4. Set root password (REMEMBER THIS!)
5. Complete installation

### 4️⃣ Create Database

**Option A: Using MySQL Command Line**
```cmd
mysql -u root -p
```
Enter your password, then:
```sql
CREATE DATABASE edumatrix;
exit;
```

**Option B: Using MySQL Workbench**
1. Open MySQL Workbench
2. Connect to localhost
3. Run: `CREATE DATABASE edumatrix;`

**Option C: Run schema file**
```cmd
cd C:\Users\amans\OneDrive\Desktop\collage project\EduMatrix
mysql -u root -p < schema.sql
```

### 5️⃣ Configure Application

1. Open: `src\main\resources\application.properties`
2. Find this line:
   ```properties
   spring.datasource.password=your_mysql_password
   ```
3. Replace `your_mysql_password` with your actual MySQL password
4. Save the file

### 6️⃣ Run the Application

**Option A: Double-click**
```
run.bat
```

**Option B: Command line**
```cmd
cd C:\Users\amans\OneDrive\Desktop\collage project\EduMatrix
mvn spring-boot:run
```

### 7️⃣ Access the Application

1. Wait for "Started EduMatrixApplication" message
2. Open browser: http://localhost:8080
3. Click "Register" to create your account
4. Login and start using EduMatrix!

## ✅ Verification Checklist

- [ ] Java 21 installed (`java -version`)
- [ ] Maven installed (`mvn -version`)
- [ ] MySQL installed and running
- [ ] Database 'edumatrix' created
- [ ] Password updated in application.properties
- [ ] Application starts without errors
- [ ] Can access http://localhost:8080

## 🐛 Common Issues

### "Port 8080 already in use"
```cmd
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### "Access denied for user 'root'"
- Check password in application.properties
- Verify MySQL is running

### "Cannot find mvn command"
- Maven not in PATH
- Restart command prompt after adding to PATH

### "Database 'edumatrix' doesn't exist"
- Run: `CREATE DATABASE edumatrix;` in MySQL

## 📞 Need Help?

If you encounter issues:
1. Check the error message carefully
2. Verify all prerequisites are installed
3. Ensure MySQL is running
4. Check application.properties configuration

---

**Ready to go! 🎉**
