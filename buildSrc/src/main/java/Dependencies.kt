object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.3"
    const val ANDROIDX_TEST = "1.4.0"
    const val APPCOMPAT = "1.3.0"
    const val CORE_KTX = "1.6.0"
    const val JUNIT = "4.13.2"
    const val ROBOLECTRIC = "4.6.1"
    const val KTLINT = "0.42.0"
}

object BuildPluginsVersion {
    const val DETEKT = "1.17.1"
    const val KTLINT = "10.1.0"
    const val VERSIONS_PLUGIN = "0.39.0"
}

object SupportLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
}
