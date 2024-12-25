# AzanReminder-App

## Features
- 🕌 **Prayer Times**: Fetch and display today's prayer times based on the user's location.
- ⏰ **Time Adjustment**: Change the selected day using back and forward arrows (limited to today).
- 💾 **Offline Mode**: Store times locally for offline access.
- 🕒 **12-Hour Format**: Show times in an easy-to-read 12-hour format.
- 🕰️ **Countdown Timer**: Display a countdown for the next prayer time on the home screen.
- 📍 **Current Location**: Show the current location on the home screen.
- 🗺️ **Interactive Map**: Navigate to a map showing Kaaba marker, current location marker, and Qibla direction.
- 🔁 **Live Qibla Direction**: Update Qibla direction dynamically using the rotation vector sensor.
- 📲 **Android Widget**: Countdown widget for prayer times.

---

## ScreenShots

<div align="center">
  <img src="https://github.com/user-attachments/assets/5faeeeab-9ffb-4d82-b7d2-3bcb7eb320e8" alt="Notification" width="200" />
  <img src="https://github.com/user-attachments/assets/fb803fd6-0e83-4049-afec-3c60667ffed2" alt="Notification Splash" width="200" />
  <img src="https://github.com/user-attachments/assets/c2f5bece-7ca4-450d-9e10-42542402ae50" alt="Splash Screen" width="200" />
  <img src="https://github.com/user-attachments/assets/429456f4-4e12-4cf6-8220-1eac919b57fe" alt="Azan Times" width="200" />
</div>

<div align="center">
  <img src="https://github.com/user-attachments/assets/bee1a898-e0eb-4623-9ac9-96475370ba12" alt="Azan Logo" width="200" />
  <img src="https://github.com/user-attachments/assets/1427dca7-31c1-40a9-9859-63b44bdbedcc" alt="Map Screen" width="200" />
</div>

---

## App Architecture
<div align="center">
  <img src="https://github.com/ahmed-faroukk/AlalmiyaAlhura-Task/assets/72602749/8eb9bff8-f516-44bd-bc5c-d5eccf78225f" alt="App Architecture" width="600" />
</div>

---

## In-App Architecture
<div align="center">
  <img src="https://github.com/ahmed-faroukk/AlalmiyaAlhura-Task/assets/72602749/a4a02bb5-58ca-4ac6-a9c6-153182644af5" alt="In-App Architecture" width="600" />
</div>

---

## Tools & APIs
- 🛠️ **Architecture**: MVVM, Clean Architecture
- 🔧 **Dependency Injection**: Dagger Hilt
- 🌐 **APIs**: Fused Location Provider API, Retrofit, OkHttp
- 🗃️ **Database**: Room DB
- 🗺️ **Mapping**: Open Street Maps
- 📅 **Work Scheduling**: WorkManager
- 💡 **UI Enhancements**: View Binding, Splash Screen, Navigation Component
- 🚀 **Kotlin Features**: Coroutines, Sealed Classes
- 📡 **Network Monitoring**: Observing network connection with Flow
- 🛡️ **Leak Detection**: LeakCanary

---

## Highlights
- 🎨 **Simple UI**: Clean and user-friendly design.
- ✨ **Smooth Animations**: Enhances user experience.
- 📂 **Caching**: For seamless offline functionality.