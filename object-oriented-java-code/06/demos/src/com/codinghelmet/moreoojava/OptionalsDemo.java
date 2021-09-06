package com.codinghelmet.moreoojava;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptionalsDemo {
    private static abstract class Maybe<T> {
        public abstract <TResult> Maybe<TResult> map(Function<T, TResult> transform);
        public abstract <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform);
        public abstract T orElse(T substitute);
        public abstract boolean isPresent();
        public abstract T get();
    }

    private static class Some<T> extends Maybe<T> {
        private T content;

        public Some(T content) {
            this.content = content;
        }

        @Override
        public <TResult> Maybe<TResult> map(Function<T, TResult> transform) {
            return new Some(transform.apply(this.content));
        }

        @Override
        public <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform) {
            return transform.apply(this.content);
        }

        @Override
        public T orElse(T substitute) {
            return this.content;
        }

        @Override
        public boolean isPresent() { return true; }

        @Override
        public T get() { return this.content; }
    }

    private static class None<T> extends Maybe<T> {
        public None() { }

        @Override
        public <TResult> Maybe<TResult> map(Function<T, TResult> transform) {
            return new None<TResult>();
        }

        @Override
        public <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform) {
            return new None<TResult>();
        }

        @Override
        public T orElse(T substitute) {
            return substitute;
        }

        @Override
        public boolean isPresent() { return false; }

        @Override
        public T get() { throw new IllegalStateException(); }
    }

    private void display(Maybe<String> value) {
        System.out.println(value.map(String::toUpperCase).orElse("Nothing to show..."));
    }

    private void displayAsSquare(Maybe<String> value) {
        System.out.println();
        this.display(this.toSquare(value));
    }

    private Maybe<String> toSquare(Maybe<String> value) {
        return value.flatMap(this::toSquare);
    }

    private Maybe<String> toSquare(String value) {
        return this.trySqrt(value.length())
                .map(columns -> this.toMatrix(value, (int)Math.ceil(columns)));
    }

    private Maybe<Double> trySqrt(int value) {
        return value < 0 ? new None<Double>()
               : new Some(Math.sqrt((double)value));
    }

    private String toMatrix(String value, int columns) {
        return this.toMatrix(value, columns, (value.length() + columns - 1) / columns);
    }

    private String toMatrix(String value, int columns, int rows) {
        return IntStream.range(0, rows).map(row -> row * columns)
                .mapToObj(startIndex -> value.substring(startIndex, Math.min(startIndex + columns, value.length())))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public void run() {
        this.display(new None());
        this.display(new Some("Something"));
        this.display(new Some("Making Your Java Code More Object-oriented"));

        this.displayAsSquare(new None());
        this.displayAsSquare(new Some("Something"));
        this.displayAsSquare(new Some("Making Your Java Code More Object-oriented"));
    }
}































