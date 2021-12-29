package org.soyphea.kafkasample.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event<T> implements Serializable {
    String topics;
    T content;
}
