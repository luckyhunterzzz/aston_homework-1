package linkedlist;

/**
 * Класс MyLinkedList представляет собой двусвязный список, реализованный без использования
 * стандартных коллекций из пакета java.util. Поддерживает операции добавления, удаления,
 * получения элемента по индексу, очистки списка, сортировка списка, вывода списка на экран и получения размера списка.
 *
 * @param <T> тип элементов, хранимых в списке
 */
public class MyLinkedList<T extends Comparable<T>> {
    private int size = 0;
    Node<T> first;
    Node<T> last;

    /**
     * Заводим внутренний вложенный private класс, который будет описывать структуру элементов нашего списка,
     * имеющий ссылки на предыдущий и последующие элементы.
     * Для возможности обращаться к внутренним полям класса, делаем внутренний класс статическим.
     *
     * @param <T> тип данных, хранимых в узле
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        /**
         * Конструктор узла с заданными данными.
         *
         * @param data данные, которые будет хранить узел
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Метод для добавление элемента в конец списка. Принимает значение data.
     * Создает объект вложенного класса с параметром data.
     *
     * @param data данные элемента для добавления
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {            //Через условный оператор if проверяем есть ли в нашем списке хотя бы один элемент,
            first = newNode;            //и если его нет, то новый узел становится как и первым, так и последним сразу.
            last = newNode;
        } else {                        //Если список не пуст:
            last.next = newNode;        //Поле текущего последнего узла "last.next" начинает ссылаться на новый элемент.
            newNode.prev = last;        //Ссылка на предыдущий элемент нового узла начинает ссылаться на текущий последний элемент.
            last = newNode;             //Последним элементом становится новый элемент.
        }
        size++;                         //size увеличивается на 1.
    }

    /**
     * Метод для добавление элемента в конец списка. Принимает значение data. Создает объект вложенного класса с параметром
     * data.
     *
     * @param index индекс, по которому следует вставить элемент
     * @param data  данные элемента для вставки
     */
    public void add(int index, T data) {
        if (index < 0 || index >= size) {   //Проверяем, что index в диапозоне списка
            throw new IndexOutOfBoundsException("Индекс: " + index + ", вне размера списка: " + size);
        }

        Node<T> newNode = new Node<>(data);
        if (index == 0) {                   //Если список пуст и индекс равен 0
            if (first == null) {            //Повторяем как при обычном add()
                first = newNode;
                last = newNode;
            } else {                        //Если список не пуст, добавляем newNode в начало списка
                newNode.next = first;       //newNode ссылается на первый элемент
                first.prev = newNode;       //Первый элемент ссылается на newNode
                first = newNode;            //newNode становится новым первым элементом списка
            }
        } else if (index == size) {         //Если индекс указывает на конец списка (добавление в конец)
            last.next = newNode;            //Последний элемент списка ссылается на newNode
            newNode.prev = last;            //newNode ссылается на предыдущий последний элемент
            last = newNode;                 //newNode становится новым последним элементом списка
        } else {                            //Добавление в середину списка по указанному индексу
            Node<T> current = first;        //Присваеваем временной переменной первый элемент списка
            for (int i = 0; i < index; i++) {
                current = current.next;     //Производим поиск необходимого элемента с помощью цикла,
            }                               //в конце наша временная переменная присвается искомуму элементу
            newNode.next = current;         //newNode ссылается на текущий узел
            newNode.prev = current.prev;    //newNode ссылается на предыдущий узел от current
            (current.prev).next = newNode;  //Устанавливаем связь между предыдущим узлом и newNode
            current.prev = newNode;         //Устанавливаем связь между newNode и current
        }
        size++;                             // Увеличиваем размер списка на 1
    }

    /**
     * Метод для получение элемента.
     *
     * @param index индекс элемента для получения
     * @return элемент списка по указанному индексу
     */
    public T get(int index) {
        if (index < 0 || index >= size) {   //Проверяем, что index в диапозоне списка
            throw new IndexOutOfBoundsException("Индекс: " + index + ", вне размера списка: " + size);
        }

        Node<T> current;
        if (index < size / 2) {                 //Если индекс меньше половины размера списка, проход будет осуществляться от начала к концу
            current = first;                    //Присваеваем временной переменной первый элемент списка
            for (int i = 0; i < index; i++) {
                current = current.next;         //Производим поиск необходимого элемента с помощью цикла,
            }                                   //в конце наша временная переменная присвается искомуму элементу
        } else {                                //Повторяем для случая если индекс больше половины размера
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.data;
    }

    /**
     * Метод для удаления элемента по индексу.
     *
     * @param index индекс элемента для удаления
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {   //Проверяем, что index в диапозоне списка
            throw new IndexOutOfBoundsException("Индекс: " + index + ", вне размера списка: " + size);
        }

        if (index == 0) {                   // Проверяем, если индекс равен 0
            first = first.next;             // Устанавливаем first на следующий элемент после текущего first
            if (first != null) {            // Если новый first не равен null, устанавливаем prev на null
                first.prev = null;
            } else {
                last = null;                // Если новый first равен null, значит список теперь пустой, устанавливаем last в null
            }
        } else if (index == size - 1) {     // Если индекс равен последнему элементу (size - 1)
            last = last.prev;               // Устанавливаем last на предыдущий элемент перед текущим last
            if (last != null) {             // Если новый last не равен null, устанавливаем next на null
                last.next = null;
            } else {                        // Если новый last равен null, значит список теперь пустой, устанавливаем first в null
                first = null;
            }
        } else {                                // Если индекс не является ни первым, ни последним элементом находим текущий узел по индексу
            Node<T> current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            (current.prev).next = current.next;   // Удаляем текущий узел из списка
            (current.next).prev = current.prev;
        }
        size--;                                   // Уменьшаем размер списка на 1
    }

    /**
     * Метод для ичистки списка
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Метод для получения размера списка
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Сортировка коллекции методом вставок.
     */
    public void sort() {
        if (first == null) {                    // Проверяем, пустой ли список
            return;                             // Если список пуст, сортировать нечего
        }

        Node<T> current = first.next;           // Начинаем сортировку со второго элемента
        while (current != null) {               // Проходим по всем элементам списка
            T key = current.data;               // Сохраняем значение текущего элемента
            Node<T> prev = current.prev;        // Устанавливаем prev на предыдущий элемент

            while (prev != null && prev.data.compareTo(key) > 0) {  // Перемещаем элементы, которые больше key, на одну позицию вперед
                (prev.next).data = prev.data;                       // Сдвигаем данные вперед
                prev = prev.prev;                                   // Переходим к предыдущему элементу
            }

            if (prev == null) {                 // Если дошли до начала списка, вставляем key в первый элемент
                first.data = key;
            } else {
                (prev.next).data = key;         // Вставляем key после элемента, который меньше или равен key
            }

            current = current.next;             // Переходим к следующему элементу для сортировки
        }
    }

    /**
     * Метод для печати списка
     */
    public void print() {
        Node<T> current = first;                    // Устанавливаем временную переменную на первый элемент списка
        while (current != null) {                   // Пока текущий узел не станет равным null продолжаем цикл
            System.out.print(current.data + " ");   // Выводим данные текущего узла на консоль
            current = current.next;                 // Переходим к следующему узлу списка
        }
        System.out.println();
    }
}
