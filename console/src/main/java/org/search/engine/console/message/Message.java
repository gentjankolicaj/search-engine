package org.search.engine.console.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<H, B> implements Serializable {
    private H header;
    private B body;


}
