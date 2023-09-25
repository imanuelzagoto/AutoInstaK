import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    if (args.size < 1) {
        println("Usage: kotlin helperGram.kt <objectiv> <target>")
        return
    }

    val objectiv = args[0]
    val target = args[1]
    val onScreen = "adb -s $objectiv shell input keyevent 224"
    val swipeCommand = "adb -s $objectiv shell input swipe 500 1500 500 500"
    val recent = "adb -s $objectiv shell input keyevent KEYCODE_APP_SWITCH"
    val close = "adb -s $objectiv shell input tap 308 1842"
    val executeGram = "adb -s $objectiv shell am start -n com.instagram.android/com.instagram.mainactivity.MainActivity"
    val clickxy = "adb -s $objectiv shell input tap 90 1012"
    val text1 = "adb -s $objectiv shell input text \"Boths\""
    val clickxy2 = "adb -s $objectiv shell input tap 90 788"
    val text2 = "adb -s $objectiv shell input text $target"
    
    // Menambahkan intent untuk mengklik koordinat tertentu
    clickCoordinate(60, 1272, objectiv) // Ganti koordinat sesuai kebutuhan

    executeAdbCommand(onScreen)
    println("On screen")
    Thread.sleep(3000)

    executeAdbCommand(swipeCommand)
    println("Swipe devices")
    Thread.sleep(3000)

    executeAdbCommand(recent)
    println("click recent success")
    Thread.sleep(3000)

    executeAdbCommand(close)
    println("click close success")
    Thread.sleep(3000)

    executeAdbCommand(executeGram)
    println("Open telegram")
    Thread.sleep(3000)

    executeAdbCommand(clickxy)
    println("click username success")
    Thread.sleep(3000)

    executeAdbCommand(text1)
    println("click typing text success")
    Thread.sleep(3000)

    executeAdbCommand(clickxy2)
    println("click username success")
    Thread.sleep(3000)

    executeAdbCommand(text2)
    println("click typing text success")
    Thread.sleep(3000)
}

fun clickCoordinate(x: Int, y: Int, objectiv: String) {
    val clickCommand = "adb -s $objectiv shell input tap $x $y"
    executeAdbCommand(clickCommand)
    println("Clicking coordinates: ($x, $y)")
}

fun executeAdbCommand(command: String) {
    val process = ProcessBuilder()
        .command("bash", "-c", command)
        .redirectErrorStream(true)
        .start()

    val inputStream = process.inputStream
    val reader = BufferedReader(InputStreamReader(inputStream))
    val output = StringBuilder()
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        output.append(line).append("\n")
    }

    process.waitFor()
}
