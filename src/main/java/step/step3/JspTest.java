
package step.step3;

public class JspTest {


    /**
     * 3. jsp 확인
     * webapp 폴더에 jsp 파일을 만들고, jsp 파일이 정상적으로 호출이 되는지 확인
     * 이때, tomcat을 통해서 실행해야 함.
     * <p>
     * <p>
     * <p>
     * 4. jsp에서 java파일로 구현된(오픈 API 정보 가져오기, DB에서 내용 가져오기가 구현된 기능 호출)
     * ex) webapp/step3.jsp 에서 JspTest.hello가 호출되는지 획인
     *
     */
    public String hello() {

        return "hello";
    }


}
