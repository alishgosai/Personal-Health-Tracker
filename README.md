# **Personal Health Tracker App**

## **By [Alish Gosai](https://alishgosai.com)**



A user-friendly Android application designed to help users monitor their health by tracking personal details, exercise routines, diet habits, and hydration levels. This app prioritizes secure data handling and provides a clean, intuitive user experience.

---

## **Features**

1. **User Profile Management**
   - Input and save personal details like name, age, weight, and height.
   - Validates inputs to ensure accurate data.

2. **Exercise Tracking**
   - Log exercise type, duration, and calories burned.
   - Helps users track and manage their fitness routines.

3. **Diet Tracking**
   - Record meal details, calories consumed, and water intake.
   - Simplified logging for improved nutrition tracking.

4. **Comprehensive Summary**
   - Displays a summary of all logged data.
   - Allows editing of specific sections for accuracy and updates.

5. **Secure Data Handling**
   - Uses Base64 encryption to protect sensitive user data during activity transitions.
   - Decrypts data in receiving activities to maintain security and usability.

---

## **How It Works**

1. **User Profile Page**
   - The app starts by collecting personal details: name, age, weight, and height.
   - Validates and encrypts the inputs before securely passing them to the next activity.

2. **Exercise Tracking Page**
   - Users log their exercise routine, including type, duration, and calories burned.
   - Data from the profile page is decrypted, combined with new inputs, and encrypted before moving to the next activity.

3. **Diet Tracking Page**
   - Users log their diet details: meal description, calories consumed, and water intake.
   - Previous data is decrypted, new inputs are added, and everything is encrypted again for secure passage.

4. **Summary Page**
   - Displays a comprehensive summary of all data collected.
   - Allows users to navigate back and edit specific sections.

---

## **Technologies Used**

- **Android Studio**: IDE for Android development.
- **Java**: Programming language for the app.
- **Material Design Components**: For creating a clean and modern UI.
- **Base64 Encryption**: For secure data passage between activities.

---

## **Code Examples**

### Data Encryption and Passing:
```java
Intent intent = new Intent(MainActivity.this, ExerciseTrackingActivity.class);
intent.putExtra("name", Base64.encodeToString(name.getBytes(), Base64.DEFAULT));
startActivity(intent);
