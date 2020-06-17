e: /Users/nakama/AndroidStudioProjects/hilt-sample/app/build/tmp/kapt3/stubs/debug/app/isfaaghyth/hilt/ui/MainActivity.java:7: error: [Hilt]
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
             ^
  Expected @AndroidEntryPoint to have a value. Did you forget to apply the Gradle Plugin?
  [1;31m[Hilt] Processing did not complete. See error above for details.[0m

added: apply plugin: 'dagger.hilt.android.plugin'