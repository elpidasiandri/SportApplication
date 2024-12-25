### Key Features:

* Room Caching: The app uses Room to cache data. If the user refreshes the app, the cache is cleared. After one day, the app will automatically fetch fresh data through an HTTP call.
* Clean Code MVVM: The application follows the Model-View-ViewModel (MVVM) architecture to separate concerns and ensure a clean and maintainable codebase.
* Kotlin Coroutines & Flows: Coroutines and Flows are used for handling asynchronous tasks and reactive data streams.
* Retrofit for HTTP Calls: Retrofit is used to manage network calls and simplify the process of communicating with external APIs.
* Koin for Dependency Injection: Koin is used to manage dependencies and facilitate easier testing and modularization of the app.
* Unit Testing: The project includes unit tests to ensure that core logic works as expected.
* Screenshot Testing: The app includes screenshot testing to ensure the UI displays correctly across different devices and configurations.
* Jetpack Compose


### Extra Features:
* Pull to Refresh: Users can pull down to refresh the content displayed in the app.
* Automatic HTTP Call After 1 Day: The app checks the time of the last HTTP call and automatically makes a new call if one day has passed. The timestamp is stored in a shared ViewModel for tracking.
* No Ripple Effect on Clicks: When a user clicks an item, the ripple effect is disabled (using noRippleClickable) to provide a cleaner interaction experience.
* Empty Screens
* Custom Toast: A custom Toast message is shown for 3 seconds in case of an error (e.g., network issues or other exceptions). This Toast is triggered in all catch blocks, including when there is no internet connection.
* Pagination in Views: Pagination is implemented in the views to efficiently handle large datasets and improve performance.


## Schema

>> sportApp
├── db
│   ├── daos                # Data Access Objects (DAO) for interacting with Room database
│   └── entities            # Room entities representing database tables
├── di                      # Modules for Dependency Injection (using Koin)
├── manager                 # SharedPreferences (stores the time for HTTP call after one day)
├── models
│   ├── domain              # Domain models
│   └── network             # Network models (used for API responses)
├── network                 # API service (Retrofit setup)
├── repositories
│   ├── database            # Repository for interacting with Room
│   └── network             # Repository for interacting with network data
├── theme                   # App themes (colors, typography, etc.)
├── ui
│   ├── components          # Reusable UI components
│   ├── emptyScreens        # Empty state screens when no data is available
│   ├── errorToast          # Custom Toast for showing error messages
│   ├── eventsOfSport       # UI representation of sports events
│   └── sportRow            # Single row view for displaying a sport event
├── useCases
│   ├── db                  # Use case for Room database operations
│   └── network             # Use case for network operations
├── utils                   # extensions
└── viewModel               # ViewModel
    └── stateAndEvents      # Contains the state and events logic for the ViewModels

