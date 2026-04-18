# Step-by-Step Instructions to Generate APK in Android Studio

## Prerequisites
1. Install Android Studio (latest stable).
2. Install Android SDK Platform 34 and Build Tools.
3. Ensure JDK 17 is available (Android Studio bundled JDK is fine).

## Open Project
1. Launch Android Studio.
2. Click Open.
3. Select the project folder: `milkman`.
4. Wait for Gradle sync to complete.

## Build Debug APK (quick testing)
1. In Android Studio menu, click Build.
2. Click Build Bundle(s) / APK(s).
3. Click Build APK(s).
4. Wait for build to finish.
5. Click the link in the notification to locate the APK.

Debug APK output path:
- `app/build/outputs/apk/debug/app-debug.apk`

## Build Release APK (for distribution)
1. Click Build -> Generate Signed Bundle / APK.
2. Choose APK and click Next.
3. Create or select an existing keystore file.
4. Fill key alias, passwords, and validity.
5. Select release build variant.
6. Enable V1 and V2 signing.
7. Click Finish.

Release APK output path:
- `app/build/outputs/apk/release/app-release.apk`

## Optional CLI Build
From project root:

```bash
./gradlew assembleDebug
```

On Windows Command Prompt:

```bat
gradlew.bat assembleDebug
```

## Install APK on Device
1. Enable Developer Options and USB Debugging on Android phone.
2. Connect device via USB.
3. Run:

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## Notes
- First login default credentials:
  - Username: admin
  - Password: admin123
- Change admin password flow can be added in next iteration.
