import java.io.FileWriter;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Main {
    public static void main(String[] args) throws IOException {
        {
            Scanner DataScanner = new Scanner(System.in);
            Scanner scanner = new Scanner(System.in);
            String filePath = "C:\\Users\\mimin\\IdeaProjects\\DiscordDealingProject\\src\\data.txt";

            // 데이터 저장
            System.out.println("데이터를 입력해주세요");
            String notProcessingData = "";
            String temp = "";

            while (DataScanner.hasNextLine()) {
                temp = DataScanner.next();
                notProcessingData += temp + "\n";
//                System.out.println("scanning data / data = " + temp);
                if (temp.equals("봇")) {
                    break;
                }
            }

//            while (scanner.hasNextLine()) {
//                Scanner lineScanner = new Scanner(scanner.nextLine());
//
//                while (lineScanner.hasNext()) {
//                    temp = lineScanner.next();
//                    notProcessingData += temp + "\n";
//                }
//                lineScanner.close();
//            }


//            System.out.println("데이터 \n" + notProcessingData);

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(notProcessingData);
            fileWriter.close();

//            System.out.println("fileWriter function done");

            FileReader reader = new FileReader(filePath);

            int ch, length = 0, i = 0, sum = 0, max = 0, min = 10000000, maxLength = 0, minLength = 0;
            int[] input = new int[1000];
            double average = 0.0, resell = 0.0;

//            System.out.println("fileReader function done");

            // 데이터 정리
            ProData marketData = new ProData();
            while ((ch = reader.read()) != -1) {
                int a = 0;

                // 시간 정보 확인
//                if (ch == 8212) { // 시간 데이터 시작 지점 (-)
//                    boolean afternoon = false; // 오후면 true 오전이면 false
//                    boolean ThisDataIsTrueData = false; // 더미 데이터 검사 / 더미가아니면 true 더미면 false
//                    for (int j = 0;j < 6;j++) { // (-)로 부터 6번 뒤에 있는 것이 시간 데이터
//                        ch = reader.read();
////                        System.out.println(ch);
//                    }
////                    System.out.println("afternoon data check / data = " + ch);
//                    if (ch == 54980) { // 오후(54980) 면 true
//                        afternoon = true;
//                        ThisDataIsTrueData = true;
//                    } else if (ch == 51204) { // 오전(51204) 면  false
//                        afternoon = false;
//                        ThisDataIsTrueData = true;
//                    } else { // 더미데이터면 false
//                        ThisDataIsTrueData = false;
////                        System.out.println("wrong data insert / this '-' is wrong position");
//                    }
////                    System.out.println(ThisDataIsTrueData);
////                    System.out.println("check point 0");
//                    if (ThisDataIsTrueData) { // 더미데이터가 아니면 실행
////                    System.out.println("befor the save / data = " + ch);
//                        String timeTamp = ""; // 시간 데이터 임시 저장
////                        System.out.println("between the save / data = " + ch);
//                        if ((ch = reader.read()) == 10) { // 스페이스 다 날려 시작지점으로 이동
////                        System.out.println("saving / data = " + ch);
//                            while ((ch = reader.read()) != 10) { // 다음줄이 나오기 전까지 데이터 입력받기
////                            System.out.println("while func inside / data = " + ch);
//                                if (ch != 58) { // : 데이터 제외 다 입력
////                                System.out.println("time save data check / data = " + ch);
//                                    timeTamp = timeTamp + String.valueOf((char)ch); // 데이터 임시 저장
//                                }
//                            }
////                        System.out.println("time data check / data = " + timeTamp);
//                            time.add(Double.valueOf(timeTamp)); // 데이터 저장
////                        System.out.println("after the save");
//                        }
//                    }
//
////                    System.out.println("check point 1");
//                    while (ch != 49) { // 가격데이터 나올때 까지 이동
//                        ch = reader.read();
////                        System.out.println("shift data check / data = " + ch);
//                        // 예외 데이터 입력
//                        if (ch == 10) {
////                            System.out.println("wrong data insert");
//                            time.remove(time.size() - 1);
//                            break;
//                        }
//                    }
//
////                    System.out.println("before the price data checker / data = " + ch);
//                    // 가격정보 확인
//                    if (ch == 49) {
//                        ch = reader.read();
//                        if (ch == 48) {
//                            ch = reader.read();
//                            if (ch == 48) {
//                                ch = reader.read();
//                                while (ch == 10) ch = reader.read();
//                                if (ch == 58) {
//                                    ch = reader.read();
//                                    while (ch == 10) ch = reader.read();
//                                    a += (ch - 48) * 10;
//                                    ch = reader.read();
//                                    a += (ch - 48);
//                                    input[length] = a;
//                                    priceData.add(Double.valueOf(a));
//                                    time.add(Double.valueOf(length));
//                                    length++;
//                                }
//                            }
//                        }
//                    } else {
////                        System.out.println("wrong data insert / price data's front is not 1");
////                        time.remove(time.size() - 1);
//                    }
//                }

                String nowTime = ""; // 현재 처리중인 데이터 시간
                if (ch == 8212) { // 시간 데이터 시작 지점 (-)
                    for (int j = 0;j < 8;j++) { // (-)로 부터 8번 뒤에 있는 것이 시간 데이터
                        ch = reader.read();
                    }
                    if ((ch = reader.read()) == 10) { // 스페이스 다 날려 시작지점으로 이동
                        while ((ch = reader.read()) != 10) { // 다음줄이 나오기 전까지 데이터 입력받기
                            nowTime += ch; // 시간 저장
                        }
                    }
                }

                if (ch == 49) {
                    ch = reader.read();
                    if (ch == 48) {
                        ch = reader.read();
                        if (ch == 48) {
                            ch = reader.read();
                            while (ch == 10) ch = reader.read();
                            if (ch == 58) {
                                ch = reader.read();
                                while (ch == 10) ch = reader.read();
                                a += (ch - 48) * 10;
                                ch = reader.read();
                                a += (ch - 48);
                                input[length] = a;
                                marketData.setData(Double.valueOf(length), Double.valueOf(a), nowTime);
                                length++;
                            }
                        }
                    }
                }
            }

//            System.out.println(Arrays.toString(input) + "\n" + Arrays.toString(priceData.toArray()) + "\n" + Arrays.toString(time.toArray()));

//            while (!(i > length)) {
//                sum += input[i];
//                if (max < input[i]) {
//                    max = input[i];
//                    maxLength = i + 1;
//                }
//                if (min > input[i] && input[i] > 1) {
//                    min = input[i];
//                    minLength = i + 1;
//                }
//                i++;
//            }
//            average = sum;
//            average /= (length);
//
//            resell = average;
//            resell *= 95;
//            resell /= 100;
//
//            marketData.timePrint();
//            marketData.numberPrint();
//            marketData.pricePrint();

            System.out.println("\n평균값 : " + marketData.getAverage() + "\n최솟값 : " + marketData.getMin()[1] + " " + marketData.getMin()[0] + " 번째줄" + "\n최댓값 : " + marketData.getMax()[1] + " " + marketData.getMax()[0] + " 번째줄" + "\n총 데이터 량 : " + marketData.length() + "\n리셀가 : " + marketData.getResell());

            // 데이터 체크
//            DoubleArrayListPrinter(time);
//            DoubleArrayListPrinter(priceData);
//            DoubleArrayListLengthPrinter(time);
//            DoubleArrayListLengthPrinter(priceData);

            // 차트 생성
            XYChart chart = QuickChart.getChart("쌀값" , "", "", "y(x)", marketData.getNumber(), marketData.getPrice());
            chart.getStyler().setYAxisMin(marketData.getMin()[1] - 5.0);
            chart.getStyler().setYAxisMax(marketData.getMax()[1] + 5.0);
            new SwingWrapper(chart).displayChart();

            System.out.println("판매 희망가를 입력해주세요");

            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("판매 수량를 입력해주세요");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.println("100:" + (int)price + " " + (int)amount + "만골 팝니다 | 거래내역 다수 & 거래내역 2년++ | 원정대 231 | 본캐대기중 | DM주세요 | 거래대기중");
            System.out.println("최종 수익금 : " + (price * amount * 100));
        }
    }

    static void DoubleArrayListPrinter(ArrayList<Double> DouArr) {
        System.out.println(DouArr.toString());
    }

    static void DoubleArrayListLengthPrinter(ArrayList<Double> DouArr) {
        System.out.println("Double Array List Length / data = " + DouArr.size());
    }

    static class ProData {

        private class DataClass {
            private String time;
            private Double price;
            private Double number;

            DataClass() {
                this.time = "";
                this.price = 0.0;
                this.number = 0.0;
            }

            DataClass(Double number, Double price, String time) {
                this.time = time;
                this.price = price;
                this.number = number;
            }

            public void set(Double number, Double price, String time) {
                this.time = time;
                this.price = price;
                this.number = number;
            }

            public String getTime() {
                return time;
            }

            public Double getPrice() {
                return price;
            }

            public Double getNumber() {
                return number;
            }
        }

        private ArrayList<DataClass> data;

        ProData() {
            data = new ArrayList<DataClass>();
        }

        public void setData(Double number, Double price, String time) {
            data.add(new DataClass(number, price, time));
        }

        public String[] getTime () {
            int i = 0;
            String[] time = new String[data.size()];
            while (data.size() > i) {
                time[i] = data.get(i).getTime();
                i++;
            }
            return time;
        }

        public double[] getNumber () {
            int i = 0;
            double[] arr = new double[data.size()];
            while (data.size() > i) {
                arr[i] = data.get(i).getNumber();
                i++;
            }
            return arr;
        }

        public double[] getPrice () {
            int i = 0;
            double[] arr = new double[data.size()];
            while (data.size() > i) {
                arr[i] = data.get(i).getPrice();
                i++;
            }
            return arr;
        }

        public String getAverage () {
            String average = "";
            int sum = 0;
            int amount = 0;
            while (amount < data.size()) {
                sum += data.get(amount).getPrice().intValue();
                amount++;
            }
            sum /= amount;
            average = String.valueOf(sum);
            return average;
        }

        public int[] getMin () {
            int[] proMin = new int[2];
            int number = 0;
            int amount = 0;
            int min = data.get(amount).getPrice().intValue();
            while (amount < data.size()) {
                if (min > data.get(amount).getPrice().intValue()) {
                    min = data.get(amount).getPrice().intValue();
                    number = data.get(amount).getNumber().intValue();
                }
                amount++;
            }
            proMin[1] = min;
            proMin[0] = number;
            return proMin;
        }

        public int[] getMax () {
            int[] proMax = new int[2];
            int number = 0;
            int amount = 0;
            int max = data.get(amount).getPrice().intValue();
            while (amount < data.size()) {
                if (max < data.get(amount).getPrice().intValue()) {
                    max = data.get(amount).getPrice().intValue();
                    number = data.get(amount).getNumber().intValue();
                }
                amount++;
            }
            proMax[1] = max;
            proMax[0] = number;
            return proMax;
        }

        public int length () {
            int length = 0;
            length = data.size();
            return length;
        }

        public double getResell () {
            double resell = 0;
            resell = Double.parseDouble(this.getAverage());
            resell *= 95;
            resell /= 100;
            return resell;
        }

        public void timePrint () {
            int i = 0;
            String[] arr = new String[data.size()];
            while (data.size() > i) {
                arr[i] = data.get(i).getTime();
                i++;
            }
            System.out.println(Arrays.toString(arr));
        }

        public void pricePrint () {
            int i = 0;
            double[] arr = new double[data.size()];
            while (data.size() > i) {
                arr[i] = data.get(i).getPrice();
                i++;
            }
            System.out.println(Arrays.toString(arr));
        }

        public void numberPrint () {
            int i = 0;
            double[] arr = new double[data.size()];
            while (data.size() > i) {
                arr[i] = data.get(i).getNumber();
                i++;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}