# 🩺 MedTrack

MedTrack es una aplicación móvil desarrollada en **Jetpack Compose** con arquitectura **MVVM**, orientada a la gestión de pacientes. Permite registrar nuevos pacientes, visualizar su historial médico, y gestionar de forma simple y rápida toda la información clínica en un solo lugar.

---

## 📸 Capturas de pantalla

![Demo]![AppGifBetter](https://github.com/user-attachments/assets/732ca458-6f01-448e-9921-4e80188cc9a8)





---

## 🚀 Características

- Registro de pacientes con validaciones
- Visualización de todos los pacientes con LazyColumn
- Historial clínico por paciente
- Onboarding mostrado solo la primera vez usando DataStore
- Navegación moderna con Navigation Component y Bottom Navigation
- UI en Jetpack Compose con Material 3
- Toasts y feedbacks para el usuario
- Soporte para selección de género con imágenes
- Fuentes personalizadas
- Manejo de fechas con `LocalDate`
- Patrón MVVM + Hilt para Inyección de Dependencias

---

## 🧠 Arquitectura

El proyecto sigue la arquitectura **MVVM (Model-View-ViewModel)** y está dividido en:

📁 data
├── database (Room, DAOs, Entities)
├── repositories
└── datastore (DataStore para el onboarding)


📁 ui
├── theme (colores, tipografías)
📁view
├── navigation (NavGraph)
└── features
├── splashscreen
├── onboarding
├── home
├── registro
└── historial
📁viewmodel
└──PacienteViewModel
└──RegistroPacienteViewmodel

📁 utils
└── clases comunes (sealed classes como GenreType)


---

## 🛠️ Tecnologías y Librerías

- Jetpack Compose
- Hilt para DI
- Room (SQLite)
- Navigation Compose
- DataStore Preferences
- Kotlin Coroutines y Flow
- Material Design 3
- ViewModel y State Management
- Iconos vectoriales y assets

---

## 📂 Cómo clonar el proyecto

```bash
git clone https://github.com/Lautaro-io/MedTrackKt.git

✅ Pendientes / Futuras mejoras
- Implementar Firebase para sincronización

- Agregar autenticación

- Historial con gráficos (Chart)

- Exportar datos como PDF

- Backups automáticos


👨‍💻 Autor
✍️ Developed by Lautaro Ildarraz
 
 
