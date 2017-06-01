package jszczepankiewicz.openpojo;

/**
 * @author jszczepankiewicz
 * @since 2017-06-01
 */
@AnnotationX
public class Pojo1 {

  private String string1;
  private Long long1;
  private Boolean bool1;

  public String getString1() {
    return string1;
  }

  public void setString1(String string1) {
    this.string1 = string1;
  }

  public Long getLong1() {
    return long1;
  }

  public void setLong1(Long long1) {
    this.long1 = long1;
  }

  public Boolean getBool1() {
    return bool1;
  }

  public void setBool1(Boolean bool1) {
    this.bool1 = bool1;
  }

  public Pojo1(){

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pojo1 pojo2 = (Pojo1) o;

    if (!string1.equals(pojo2.string1)) return false;
    if (!long1.equals(pojo2.long1)) return false;
    return bool1.equals(pojo2.bool1);
  }

  @Override
  public int hashCode() {
    int result = string1.hashCode();
    result = 31 * result + long1.hashCode();
    result = 31 * result + bool1.hashCode();
    return result;
  }
}
