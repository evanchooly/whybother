package com.antwerkz.why;

import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class JavaListogram implements List<String> {
    private List<String> list = new ArrayList<>();
    private List<JavaPair<LocalTime, Integer>> history = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return list.contains(o);
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull final T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(final String s) {
        final boolean add = list.add(s);
        history.add(new JavaPair<>(LocalTime.now(), size()));
        return add;
    }

    @Override
    public boolean remove(final Object o) {
        final boolean remove = list.remove(o);
        history.add(new JavaPair<>(LocalTime.now(), size()));
        return remove;
    }

    @Override
    public boolean containsAll(@NotNull final Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull final Collection<? extends String> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(final int index, @NotNull final Collection<? extends String> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull final Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull final Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void replaceAll(final UnaryOperator<String> operator) {
        list.replaceAll(operator);
    }

    @Override
    public void sort(final Comparator<? super String> c) {
        list.sort(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean equals(final Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public String get(final int index) {
        return list.get(index);
    }

    @Override
    public String set(final int index, final String element) {
        return list.set(index, element);
    }

    @Override
    public void add(final int index, final String element) {
        list.add(index, element);
    }

    @Override
    public String remove(final int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(final Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return list.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<String> listIterator() {
        return list.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<String> listIterator(final int index) {
        return list.listIterator(index);
    }

    @NotNull
    @Override
    public List<String> subList(final int fromIndex, final int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<String> spliterator() {
        return list.spliterator();
    }

    @Override
    public boolean removeIf(final Predicate<? super String> filter) {
        return list.removeIf(filter);
    }

    @Override
    public Stream<String> stream() {
        return list.stream();
    }

    @Override
    public Stream<String> parallelStream() {
        return list.parallelStream();
    }

    @Override
    public void forEach(final Consumer<? super String> action) {
        list.forEach(action);
    }

/*
    fun histogram() {
           val map = history.groupBy { it.first.toEpochSecond(ZoneOffset.UTC) }
                   .map { it.value.last() }
                   .map { it.first.format(ofPattern("hh:mm:ss")) to (0..it.second).fold("", { acc, i -> acc + "*" }) }

           map.forEach {
               println("${it.first}: ${it.second}")
           }
       }
*/

    void histogram() {
        final Map<LocalTime, List<JavaPair<LocalTime, Integer>>> map =
            history.stream()
                   .collect(groupingBy(JavaPair::first));

        map.entrySet().stream()
           .map(entry -> entry.getValue().get(entry.getValue().size() - 1))
           .map(pair -> new JavaPair<>(pair.first.format(ofPattern("hh:mm:ss")), plot(pair.second)))
           .forEach(x -> System.out.printf("%s: %s\n", x.first, x.second));
    }

    private String plot(final int second) {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < second; x++) {
            builder.append("*");
        }
        return builder.toString();
    }
}

class JavaPair<F, S> {
    F first;
    S second;

    JavaPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    F first() {
        return first;
    }

    S second() {
        return second;
    }


    @Override
    public String toString() {
        return format("(%s, %s)", first, second);
    }
}