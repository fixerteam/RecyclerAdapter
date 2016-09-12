package io.github.fixerteam.recycleradapter;

import android.app.Application;
import java.lang.reflect.Method;
import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

public class TestRunner
    extends RobolectricTestRunner {

  private static final int SDK_EMULATE_LEVEL = 23;

  /**
   * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file and
   * res directory by default. Use the {@link Config} annotation to configure.
   *
   * @param testClass the test class to be run
   *
   * @throws InitializationError if junit says so
   */
  public TestRunner(Class<?> testClass)
      throws InitializationError {
    super(testClass);
  }

  @Override
  public Config getConfig(Method method) {
    Config defaultConfig = super.getConfig(method);
    return new Config.Implementation(new int[] { TestRunner.SDK_EMULATE_LEVEL }, defaultConfig.manifest(),
        defaultConfig.qualifiers(), defaultConfig.packageName(), defaultConfig.abiSplit(), defaultConfig.resourceDir(),
        defaultConfig.assetDir(), defaultConfig.buildDir(), defaultConfig.shadows(),
        defaultConfig.instrumentedPackages(), Application.class, defaultConfig.libraries(),
        defaultConfig.constants() == Void.class ? BuildConfig.class : defaultConfig.constants());
  }
}
