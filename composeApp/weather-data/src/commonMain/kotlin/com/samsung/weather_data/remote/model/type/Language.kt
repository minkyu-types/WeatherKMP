package com.samsung.weather_data.remote.model.type

enum class Language(val code: String) {
    SQ("sq"),          // Albanian
    AF("af"),          // Afrikaans
    AR("ar"),          // Arabic
    AZ("az"),          // Azerbaijani
    EU("eu"),          // Basque
    BE("be"),          // Belarusian
    BG("bg"),          // Bulgarian
    CA("ca"),          // Catalan
    ZH_CN("zh_cn"),    // Chinese Simplified
    ZH_TW("zh_tw"),    // Chinese Traditional
    HR("hr"),          // Croatian
    CZ("cz"),          // Czech
    DA("da"),          // Danish
    NL("nl"),          // Dutch
    EN("en"),          // English
    FI("fi"),          // Finnish
    FR("fr"),          // French
    GL("gl"),          // Galician
    DE("de"),          // German
    EL("el"),          // Greek
    HE("he"),          // Hebrew
    HI("hi"),          // Hindi
    HU("hu"),          // Hungarian
    IS("is"),          // Icelandic
    ID("id"),          // Indonesian
    IT("it"),          // Italian
    JA("ja"),          // Japanese
    KR("kr"),          // Korean
    KU("ku"),          // Kurmanji (Kurdish)
    LA("la"),          // Latvian
    LT("lt"),          // Lithuanian
    MK("mk"),          // Macedonian
    NO("no"),          // Norwegian
    FA("fa"),          // Persian (Farsi)
    PL("pl"),          // Polish
    PT("pt"),          // Portuguese
    PT_BR("pt_br"),    // Português Brasil
    RO("ro"),          // Romanian
    RU("ru"),          // Russian
    SR("sr"),          // Serbian
    SK("sk"),          // Slovak
    SL("sl"),          // Slovenian
    ES("es"),          // Spanish (alias sp)
    SV("sv"),          // Swedish (alias se)
    TH("th"),          // Thai
    TR("tr"),          // Turkish
    UK("uk"),          // Ukrainian (alias ua)
    VI("vi"),          // Vietnamese
    ZU("zu");          // Zulu

    companion object {
        fun fromCode(code: String): Language? =
            entries.firstOrNull { it.code.equals(code, ignoreCase = true) }
    }
}
