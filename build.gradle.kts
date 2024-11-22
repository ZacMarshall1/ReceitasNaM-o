plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false


    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    kotlin("plugin.serialization") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false

}
