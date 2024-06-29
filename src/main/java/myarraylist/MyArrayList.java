package myarraylist;

/**
 * Реализовать свой ArrayList и LinkedList (не потокобезопасный)
 * Методы – добавить элемент, добавить элемент по индексу, получить элемент,
 * удалить элемент, очистить всю коллекцию, отсортировать, остальное по желанию.
 *
 * @param <T> тип элементов, хранимых в списке
 */
public class MyArrayList<T extends Comparable<T>> {
    private int size;
    private T[] list;

    /**
     * Конструктор для инициализации списка с начальной емкостью 10 элементов.
     */
    public MyArrayList() {
        this.list = (T[]) new Comparable[10];
        this.size = 0;
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return текущий размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Очищает список, устанавливая его размер в 0 и создавая новый внутренний массив с емкостью 10 элементов.
     */
    public void clean() {
        this.list = (T[]) new Comparable[10];
        this.size = 0;
    }

    /**
     * Возвращает элемент списка по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент списка по указанному индексу, или null, если индекс вне диапазона
     */
    public T getByIndex(int index) {
        if (index < 0 || index >= size) {   //Проверяем, что index в диапозоне списка
            throw new IndexOutOfBoundsException("Индекс: " + index + ", вне размера списка: " + size);
        }
        return list[index];
    }


    /**
     * Удаляет элемент списка по указанному индексу.
     *
     * @param index индекс элемента для удаления
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {   //Проверяем, что index в диапозоне списка
            throw new IndexOutOfBoundsException("Индекс: " + index + ", вне размера списка: " + size);
        }
        for (int i = index; i < list.length - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
    }

    /**
     * Добавляет элемент в конец списка. Если внутренний массив заполнен, происходит его расширение.
     *
     * @param str элемент для добавления
     */
    public void add(T str) {
        if (size == list.length) {
            resize();
        }
        list[size++] = str;
    }

    /**
     * Добавляет элемент в список по указанному индексу, заменяя текущий элемент.
     *
     * @param index индекс, куда следует добавить элемент
     * @param str   элемент для добавления
     */
    public void add(int index, T str) {
        if (index < 0 || index >= size) {   //Проверяем, что index в диапозоне списка
            throw new IndexOutOfBoundsException("Индекс: " + index + ", вне размера списка: " + size);
        }
        if (size == list.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }

        list[index] = str;
        size++;

    }

    /**
     * Добавляет элемент в список по указанному индексу, заменяя текущий элемент.
     *
     * @param index индекс, куда следует добавить элемент
     * @param str   элемент для добавления
     */
    public void addByIndexWithChange(int index, T str) {
        if (index < 0 || index >= size) {   //Проверяем, что index в диапозоне списка
            throw new IndexOutOfBoundsException("Индекс: " + index + ", вне размера списка: " + size);
        }
        list[index] = str;
    }

    /**
     * Увеличивает емкость внутреннего массива списка в 1.5 раза + 1 элемент и копирует все текущие элементы.
     */
    private void resize() {
        int newSize = size * 3 / 2 + 1;
        T[] newList = (T[]) new Comparable[newSize];
        System.arraycopy(list, 0, newList, 0, list.length);
        this.list = newList;
    }

    /**
     * Сортирует список методом вставок.
     */
    public void sort() {
        for (int i = 1; i < size; i++) {
            T key = list[i];
            int j = i - 1;
            while (j >= 0 && list[j].compareTo(key) > 0) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
    }

    /**
     * Возвращает строковое представление списка, состоящее из элементов, разделенных пробелами и заключенных в квадратные скобки.
     *
     * @return строковое представление списка
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }
}
