# YourMarket - Android App 

<img src="/readme/appIcon.png" align="left"
width="200" hspace="10" vspace="10">

YourMarket is a product search app.  
It consumes MercadoLibre open products [API](https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas) . There's no need for an account to start using the app.

## About

YourMarket is a project made for MercadoLibre API usage demonstration. It contains a main home screen that has a search bar where you can start searching products. Once a product in particular is searched for, a list catalog containing the posible results shows up. Once a product catches your attention you can click and learn more detail about it.

## Features

The android app lets you:
- Search for a product in particular
- See posible search results
- Click on a product in particular to learn more about it

## Screenshots

<p align="left">
  <img alt="Splash Screen" src="/readme/splash_sample.gif" width="200">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="Search and product detail Screen" src="/readme/product_sample.gif"width="200">
</p>

# Install, Configure & Run

Below mentioned are the steps to install, configure & run in your platform/distributions.

```bash
# Clone the repo.
git clone https://github.com/lucastroport/meli-challenge.git

# Open the app via latest Android studio.
The dependencies will be downloaded;

# Run the app by Android studio
```

### This project features:
* Built using MVVM Architecture
*   User Interface built with **[Jetpack Compose](https://developer.android.com/jetpack/compose)** 
*   A single-activity architecture, using **[Navigation Compose](https://developer.android.com/jetpack/compose/navigation)**.
*   A presentation layer that contains Compose screens (View) and a **ViewModel** per screen (or feature).
* Networking featuring **[Retrofit](https://square.github.io/retrofit/)** library 
*   Reactive UIs using **[Flow](https://developer.android.com/kotlin/flow)** and **[coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** for asynchronous operations.
*   A **data layer** with a repository and two data sources (local using **[Room](https://developer.android.com/jetpack/androidx/releases/room?gclsrc=ds&gclsrc=ds)** and a remote one using mercado libre api service).
*   A collection of unit **tests** for the data, domain and presentation layers.
*   Dependency injection using **[Koin](https://insert-koin.io/)**
* Image loading done by **[Coil](https://coil-kt.github.io/coil/compose/)**


# [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-network-db) with compose architecture

<img alt="paging3_compose" src="/readme/paging_compose_arch.png">