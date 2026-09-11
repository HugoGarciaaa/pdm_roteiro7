# Smart Home Config App Implementation Plan

This plan outlines the development of a "Smart Home Configuration" app using a single Activity and multiple Fragments.

## User Review Required

> [!IMPORTANT]
> The app will use `FragmentContainerView` and `FragmentManager` for navigation. Each fragment will communicate with the `MainActivity` via a custom interface.

## Proposed Changes

### UI Resources

#### [NEW] [Vector Icons](file:///C:/Users/Hugo_/AndroidStudioProjects/MyApplication4/app/src/main/res/drawable/)
- `ic_home.xml`: Icon for Main Menu.
- `ic_person.xml`: Icon for Profile.
- `ic_devices.xml`: Icon for Devices.
- `ic_security.xml`: Icon for Security.

#### [MODIFY] [activity_main.xml](file:///C:/Users/Hugo_/AndroidStudioProjects/MyApplication4/app/src/main/res/layout/activity_main.xml)
- Add a `TextView` at the top to display status updates from Fragments.
- Add a `FragmentContainerView` for hosting fragments.
- Add a bottom layout with 4 `ImageButton` elements for navigation.

#### [NEW] [Fragment Layouts](file:///C:/Users/Hugo_/AndroidStudioProjects/MyApplication4/app/src/main/res/layout/)
- `fragment_main_menu.xml`: A welcome screen with information.
- `fragment_profile.xml`: Contains an `EditText` for name and a "Save" button.
- `fragment_devices.xml`: Contains `Switch` components for "Smart Lights" and "Air Conditioning".
- `fragment_security.xml`: Contains a `ToggleButton` for "Two-Factor Authentication".

### Logic & Implementation

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Hugo_/AndroidStudioProjects/MyApplication4/app/src/main/java/com/example/myapplication/MainActivity.kt)
- Implement navigation logic using `supportFragmentManager`.
- Implement callback interfaces from all 4 fragments.
- Handle the Back Stack to ensure the "Back" button returns to the Main Menu.
- Update a "Status Bar" `TextView` whenever a fragment sends data.

#### [NEW] [Fragments](file:///C:/Users/Hugo_/AndroidStudioProjects/MyApplication4/app/src/main/java/com/example/myapplication/)
- `MainMenuFragment.kt`: Initial fragment.
- `ProfileFragment.kt`: Handles name updates via interface.
- `DevicesFragment.kt`: Handles device toggles via interface.
- `SecurityFragment.kt`: Handles security settings via interface.

## Verification Plan

### Manual Verification
- Deploy to an emulator or physical device.
- Click each navigation button to ensure the correct fragment loads.
- Enter a name in the Profile fragment and verify it updates the MainActivity's status text.
- Toggle switches in Devices/Security fragments and verify the MainActivity's status updates.
- Press the device "Back" button from any sub-menu and verify it returns to the Main Menu instead of closing the app.
