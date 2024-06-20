package org.search.engine.console.message;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<H, B> implements Serializable {

  private H header;
  private B body;


}
