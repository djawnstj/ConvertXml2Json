# ConvertXml2Json

# 실행 방법

1. jar 파일 사용 방법
    1. jar 파일과 같은 경로에 target 폴더 생성.
    2. target 폴더에 변환하고자 하는 xml 파일(들)을 생성.
    3. jar 실행
        1. ConvertXml2Json-1.0-SNAPSHOT.jar 더블 클릭.
        2. 터미널에서 jar 파일 경로로 이동. `java -jar ConvertXml2Json-1.0-SNAPSHOT.jar` 입력. (파일명 변경 가능)
    4. jar 파일과 같은 경로에 output 폴더(자동 생성) 내 json 파일(들) 확인.

2. IntelliJ(Android Studio), VS Code, eclipse 등 IDE 사용
    1. 프로젝트 내 `src/main/resources` 하위에 target 폴더 생성.
    2. target 폴더에 변환하고자 하는 xml 파일(들)을 생성.
    3. `src/main/java` 하위 com.djawnstj 패키지의 Main::main 메서드 실행.
    4. 프로젝트 내 `build/resources` 하위에 output 폴더(자동 생성) 내 json 파일(들) 확인. (gradle 빌드 기준. 빌드 환경에 따라 경로가 다를 수 있음.)

# 코드 수정

만약 코드가 수정되어야 한다면 수정 후 `src/test/java/djawnstj/xml/XmlTest` 클래스를 실행하는 것을 권장.

# jar 파일 생성 방법

java 에서 jar 파일을 만들면 사용한 라이브러리(외부 jar 파일)는 포함되지 않습니다.
외부 jar 가 포함 되는 jar 를 'fatJar` 라고 하는데, fatJar 를 만들기 위해서는 라이브러리를 가져다 사용하듯이 다른 개발자가 만든 빌드 방법을 사용해야 합니다.
현재 프로젝트(1.0-SNAPSHOT, 24.10.10. by EJS) 기준 shadowJar 라는 빌드 방법을 사용했습니다.

1. Windows 에서 fatJar 생성
    1. 터미널에서 프로잭트 내부 경로 이동.
    2. `./gradlew.bat shadowJar` 입력.
    3. `build/libs` 내부 jar 파일 확인. (gradle 버전에 따라 경로가 달라질 수 있음.)

2. Mac OS/Linux 등 UNIX 계열 운영체제에서 fatJar 생성
    1. 터미널에서 프로잭트 내부 경로 이동.
    2. `./gradlew shadowJar` 입력.
    3. `build/libs` 내부 jar 파일 확인. (gradle 버전에 따라 경로가 달라질 수 있음.)
