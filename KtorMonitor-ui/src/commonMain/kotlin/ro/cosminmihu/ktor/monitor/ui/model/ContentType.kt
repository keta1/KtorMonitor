package ro.cosminmihu.ktor.monitor.ui.model

public enum class ContentType(
    public val contentType: String,
    public val contentName: String,
    public val color: String,
) {
    // Application Types
    APPLICATION_ANY("application/*", "APP", "#FFD700"),
    APPLICATION_ATOM("application/atom+xml", "ATOM", "#FFD700"),
    APPLICATION_CBOR("application/cbor", "CBOR", "#FFD700"),
    APPLICATION_JSON("application/json", "JSON", "#FF69B4"),
    APPLICATION_HAL_JSON("application/hal+json", "HAL", "#FF69B4"),
    APPLICATION_JAVASCRIPT("application/javascript", "JS", "#ADD8E6"),
    APPLICATION_OCTET_STREAM("application/octet-stream", "BIN", "#A0522D"),
    APPLICATION_RSS("application/rss+xml", "RSS", "#FFD700"),
    APPLICATION_SOAP("application/soap+xml", "SOAP", "#FFD700"),
    APPLICATION_XML("application/xml", "XML", "#FFD700"),
    APPLICATION_XML_DTD("application/xml-dtd", "DTD", "#FFD700"),
    APPLICATION_XAML("application/xaml+xml", "XAML", "#FFD700"),
    APPLICATION_ZIP("application/zip", "ZIP", "#708090"),
    APPLICATION_GZIP("application/gzip", "GZ", "#778899"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded", "FORM", "#DA70D6"),
    APPLICATION_PDF("application/pdf", "PDF", "#FF6347"),
    APPLICATION_PROTOBUF("application/protobuf", "PROTO", "#FFD700"),
    APPLICATION_WASM("application/wasm", "WASM", "#008080"),
    APPLICATION_PROBLEM_JSON("application/problem+json", "PROB", "#FF69B4"),
    APPLICATION_PROBLEM_XML("application/problem+xml", "PROB", "#FFD700"),
    APPLICATION_VND_API_JSON("application/vnd.api+json", "API", "#FF1493"),

    // Microsoft Office Word
    APPLICATION_MSWORD("application/msword", "DOC", "#1E90FF"),
    APPLICATION_WORD_OPENXML("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "DOCX", "#00008B"),
    APPLICATION_WORD_MACRO("application/vnd.ms-word.document.macroEnabled.12", "DOCM", "#8A2BE2"),
    APPLICATION_WORD_TEMPLATE("application/vnd.openxmlformats-officedocument.wordprocessingml.template", "DOTX", "#9932CC"),
    APPLICATION_WORD_TEMPLATE_MACRO("application/vnd.ms-word.template.macroEnabled.12", "DOTM", "#9400D3"),

    // Microsoft Office Excel
    APPLICATION_EXCEL("application/vnd.ms-excel", "XLS", "#32CD32"),
    APPLICATION_EXCEL_OPENXML("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "XLSX", "#228B22"),
    APPLICATION_EXCEL_MACRO("application/vnd.ms-excel.sheet.macroEnabled.12", "XLSM", "#006400"),
    APPLICATION_EXCEL_TEMPLATE("application/vnd.openxmlformats-officedocument.spreadsheetml.template", "XLTX", "#3CB371"),
    APPLICATION_EXCEL_TEMPLATE_MACRO("application/vnd.ms-excel.template.macroEnabled.12", "XLTM", "#2E8B57"),
    APPLICATION_EXCEL_BINARY("application/vnd.ms-excel.sheet.binary.macroEnabled.12", "XLSB", "#008000"),

    // Microsoft Office PowerPoint
    APPLICATION_POWERPOINT("application/vnd.ms-powerpoint", "PPT", "#FF8C00"),
    APPLICATION_POWERPOINT_OPENXML("application/vnd.openxmlformats-officedocument.presentationml.presentation", "PPTX", "#FF4500"),
    APPLICATION_POWERPOINT_MACRO("application/vnd.ms-powerpoint.presentation.macroEnabled.12", "PPTM", "#DC143C"),
    APPLICATION_POWERPOINT_TEMPLATE("application/vnd.openxmlformats-officedocument.presentationml.template", "POTX", "#FF6347"),
    APPLICATION_POWERPOINT_TEMPLATE_MACRO("application/vnd.ms-powerpoint.template.macroEnabled.12", "POTM", "#B22222"),
    APPLICATION_POWERPOINT_SLIDESHOW("application/vnd.openxmlformats-officedocument.presentationml.slideshow", "PPSX", "#CD5C5C"),
    APPLICATION_POWERPOINT_SLIDESHOW_MACRO("application/vnd.ms-powerpoint.slideshow.macroEnabled.12", "PPSM", "#A52A2A"),

    // Microsoft Office Access
    APPLICATION_ACCESS("application/vnd.ms-access", "MDB", "#800000"),
    APPLICATION_ACCESS_MODERN("application/vnd.ms-access", "ACCDB", "#8B0000"),

    // Microsoft Visio
    APPLICATION_VISIO("application/vnd.visio", "VSD", "#4682B4"),
    APPLICATION_VISIO_OPENXML("application/vnd.ms-visio.drawing", "VSDX", "#5F9EA0"),
    APPLICATION_VISIO_MACRO("application/vnd.ms-visio.drawing.macroEnabled.12", "VSDM", "#6495ED"),

    // Audio Types
    AUDIO_ANY("audio/*", "AUDIO", "#87CEFA"),
    AUDIO_MPEG("audio/mpeg", "MP3", "#87CEFA"),
    AUDIO_OGG("audio/ogg", "OGG", "#B0E0E6"),
    AUDIO_WAV("audio/wav", "WAV", "#4682B4"),
    AUDIO_WEBM("audio/webm", "WEBM", "#00CED1"),
    AUDIO_FLAC("audio/flac", "FLAC", "#8B0000"),
    AUDIO_X_WAV("audio/x-wav", "XWAV", "#4682B4"),

    // Image Types
    IMAGE_ANY("image/*", "IMG", "#FFB6C1"),
    IMAGE_GIF("image/gif", "GIF", "#FFD700"),
    IMAGE_JPEG("image/jpeg", "JPEG", "#FFB6C1"),
    IMAGE_PNG("image/png", "PNG", "#98FB98"),
    IMAGE_WEBP("image/webp", "WEBP", "#20B2AA"),
    IMAGE_SVG("image/svg+xml", "SVG", "#DA70D6"),
    IMAGE_X_ICON("image/x-icon", "ICO", "#FF8C00"),
    IMAGE_BMP("image/bmp", "BMP", "#FFD700"),

    // Text Types
    TEXT_ANY("text/*", "TXT", "#FFA07A"),
    TEXT_PLAIN("text/plain", "TXT", "#FFA07A"),
    TEXT_CSS("text/css", "CSS", "#90EE90"),
    TEXT_CSV("text/csv", "CSV", "#90EE90"),
    TEXT_HTML("text/html", "HTML", "#D3D3D3"),
    TEXT_JAVASCRIPT("text/javascript", "JS", "#ADD8E6"),
    TEXT_VCARD("text/vcard", "VCARD", "#FFA07A"),
    TEXT_XML("text/xml", "XML", "#FFD700"),
    TEXT_EVENT_STREAM("text/event-stream", "EVENT", "#A0522D"),

    // Video Types
    VIDEO_ANY("video/*", "MP4", "#FFDAB9"),
    VIDEO_MP4("video/mp4", "MP4", "#FFDAB9"),
    VIDEO_MPEG("video/mpeg", "MPEG", "#DAA520"),
    VIDEO_OGG("video/ogg", "OGV", "#F0E68C"),
    VIDEO_WEBM("video/webm", "WEBM", "#ADFF2F"),
    VIDEO_QUICKTIME("video/quicktime", "QTFF", "#FFDAB9"),
    VIDEO_X_MS_WMV("video/x-ms-wmv", "WMV", "#DAA520"),

    // Font Types
    FONT_ANY("font/*", "FONT", "#FFDAB9"),
    FONT_COLLECTION("font/collection", "TTC", "#FFDAB9"),
    FONT_OTF("font/otf", "OTF", "#FFDAB9"),
    FONT_SFNT("font/sfnt", "SFNT", "#FFDAB9"),
    FONT_WOFF("font/woff", "WOFF", "#FFDAB9"),
    FONT_WOFF2("font/woff2", "WOFF2", "#FFDAB9"),
    FONT_EOT("font/eot", "EOT", "#FFDAB9"),

    // Multipart Types
    MULTIPART_ANY("multipart/*", "PART", "#FF4500"),
    MULTIPART_MIXED("multipart/mixed", "MIX", "#FF69B4"),
    MULTIPART_ALTERNATIVE("multipart/alternative", "ALT", "#FF69B4"),
    MULTIPART_RELATED("multipart/related", "RLT", "#FF69B4"),
    MULTIPART_FORM_DATA("multipart/form-data", "FORM", "#FF4500"),
    MULTIPART_SIGNED("multipart/signed", "SIGN", "#FF4500"),
    MULTIPART_ENCRYPTED("multipart/encrypted", "CRYPT", "#FF4500"),
    MULTIPART_BYTE_RANGES("multipart/byteranges", "BYTES", "#DAA520"),

    UNKNOWN("", "?", "#808080");
}

public val String.contentType: ContentType
    get() = ContentType.entries
        .firstOrNull { this.contains(it.contentType, true) }
        ?: ContentType.UNKNOWN

internal val ContentType.asColor
    get() = color.toColor()
