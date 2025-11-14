🌟 AuraSense – Smart Health & Task Management App

AuraSense is an elegant and intuitive Android application built using Kotlin + MVVM, designed to help users manage tasks, track health activities, and stay organized with a modern, smooth UI/UX.
This app integrates Room, Retrofit, WorkManager, LiveData, ViewBinding/DataBinding, and includes themes, analytics, categories, progress tracking, and SOS support.

✨ Features
✅ Task Management

Create, update, delete tasks

Mark tasks as completed

Categorize tasks (Work, Personal, Urgent, Health, etc.)

Smooth UI with RecyclerView + DiffUtil

💙 Health Tracking

Daily health entries

Auto-sync using Retrofit (API-ready)

Progress bar to monitor daily activity

Scheduled background sync via WorkManager

🔒 Emergency SOS (Safe Implementation)

One-tap SOS using ACTION_SENDTO

Sends pre-filled SMS to emergency contacts

🎨 UI/UX Highlights

Attractive splash screen

Light and Dark themes

Background images and smooth animations

MotionLayout support

Clean MVVM architecture

🏗 Project Architecture (MVVM)
com.example.aurasense
│
├── data
│   ├── local (Room)
│   ├── remote (Retrofit)
│   └── repository
│
├── ui
│   ├── activities
│   ├── fragments
│   └── viewmodels
│
├── utils
└── model

🛠 Tech Stack
Technology	Purpose
Kotlin	Core language
MVVM Architecture	Scalable & maintainable
Room Database	Local storage
Retrofit	Network calls
WorkManager	Background sync
ViewBinding / DataBinding	Clean UI
LiveData / Coroutines	Reactive data
MotionLayout	Smooth UI animations
Material Design 3	Modern UI components
🚀 Getting Started
1️⃣ Clone the Project
git clone https://github.com/your-username/AuraSense.git

2️⃣ Open in Android Studio

File → Open → select the project folder

Let Gradle sync

3️⃣ Run the App

Select an emulator or real device

Click ▶ Run

📂 Project Structure Screenshots

(Add your screenshots here)

🧪 API Integration (Optional)

Add your API base URL inside:

RetrofitInstance.kt

🔮 Future Enhancements

Google Fit integration

Voice command task creation

Smart reminders

Cloud backup

🙌 Contributing

Pull requests are welcome!
For major changes, please open an issue first to discuss what you’d like to improve.

📜 License

This project is licensed under the MIT License.

💡 Author

Pratyush Kiran Rath
Developer – Android | Kotlin | MVVM
