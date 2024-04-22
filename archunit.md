# ArchUnit

## Description

- [Arch Unit](https://www.archunit.org/): Hace cumplir arquitectura y diseño de código mediante reglas.

- Modulo [archunit-rules](quality-rules%2Farchunit-rules) con un paquete de reglas customizadas lista para utilizar
  
- Se incluye 1 perfil en este pom para usar archunit con el maven plugin https://github.com/societe-generale/arch-unit-maven-plugin
  
- También se incluye una manera de ejecutar los tests de la librería directamente como Unit Tests en tu proyecto: [ArchUnitRulesTest.java](src%2Ftest%2Fjava%2Fes%2Ftorres%2Fbooks%2Farchunit%2FArchUnitRulesTest.java)

        <profile>
            <id>archunit</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>com.societegenerale.commons</groupId>
                        <artifactId>arch-unit-maven-plugin</artifactId>
                        <version>4.0.2</version>
                        <configuration>

                            <!-- optional - you can avoid build fail if there is issue. True to avoid build failure, default is false -->
                            <noFailOnError>true</noFailOnError>

                            <!-- optional - you can exclude classes that have a path containing any of the mentioned paths -->
                            <excludedPaths>
                                <excludedPath>generated-sources</excludedPath>
                            </excludedPaths>

                            <rules>
                                <!-- using a rule available out of the box... -->
                                <preConfiguredRules>
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoStandardStreamRuleTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoJunitAssertRuleTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoJodaTimeRuleTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoJavaUtilDateRuleTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoPowerMockRuleTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoPrefixForInterfacesRuleTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoPublicFieldRuleTest</rule>-->

                                    <!-- you may want to use one of the below rules, but not both at same time -->
                                    <!--                                <rule>com.societegenerale.commons.plugin.rules.NoTestIgnoreRuleTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoTestIgnoreWithoutCommentRuleTest</rule>-->

                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoInjectedFieldTest</rule>-->
                                    <!--                  <rule>com.societegenerale.commons.plugin.rules.NoAutowiredFieldTest</rule>-->
                                </preConfiguredRules>
                                <!-- ... and a custom one, coming from a dependency of the plugin -->
                                <configurableRules>
                                    <configurableRule>
                                        <rule>com.kalyp.buildingblocks.archunit.ArchitectureRules</rule>
                                    </configurableRule>
                                    <!--                                <configurableRule>-->
                                    <!--                                    <rule>com.tngtech.archunit.library.GeneralCodingRules</rule>-->
                                    <!--                                    <applyOn>-->
                                    <!--                                        <packageName>es.torres</packageName>-->
                                    <!--                                        &lt;!&ndash; scope can be "main" or "test" &ndash;&gt;-->
                                    <!--                                        <scope>main</scope>-->
                                    <!--                                    </applyOn>-->

                                    <!--                                    <checks>-->
                                    <!--                                        &lt;!&ndash; otherwise you can specify either field or method names here. If no checks block is defined, all are executed &ndash;&gt;-->
                                    <!--                                        <check>NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS</check>-->
                                    <!--                                    </checks>-->
                                    <!--                                </configurableRule>-->
                                </configurableRules>
                            </rules>
                        </configuration>
                        <executions>
                            <execution>
                                <phase>test</phase>
                                <goals>
                                    <goal>arch-test</goal>
                                </goals>
                            </execution>
                        </executions>
                        <dependencies>
                            <dependency>
                                <!-- dependency contains com.mycompany.rules.CustomArchRule -->
                                <groupId>es.torres</groupId>
                                <artifactId>archunit-rules</artifactId>
                                <version>0.0.1</version>
                            </dependency>
                        </dependencies>
                    </plugin>
                </plugins>
            </build>
        </profile>

## Usage

    mvn clean test -Parchunit

## Links

- .Net version: https://archunitnet.readthedocs.io/en/latest/
- Typescript/JS version: https://ts-arch.github.io/ts-arch/


