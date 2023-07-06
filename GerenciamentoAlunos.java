import java.util.Scanner;

public class GerenciamentoAlunos {

    public static void exibirLista(String[][] matriz, int linhas) {
        for (int i = 0; i < 20; i++) {
            System.out.print("=-");
        }
        System.out.println(" ");
        System.out.println("NOME | ID | TURMA | NÚMERO DE FALTAS | NOTA EM BIOLOGIA | NOTA EM MATEMÁTICA | NOTA EM PORTUGUÊS");
        for (int i = 0; i < linhas; i++) {
            System.out.print((i + 1) + " - ");
            for (int j = 0; j < 7; j++) {
                if (matriz[i][1] == null) {
                    break;
                } else {
                    if (matriz[i][j] == null) {
                        matriz[i][j] = " - ";
                    }
                    System.out.print(matriz[i][j] + " | ");
                }
            }
            System.out.println(" ");
        }
    }

    public static void exibirNomes(String[][] matriz, int linhas) {
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null) {
                System.out.println("Id: " + matriz[i][1] + " - Nome: " + matriz[i][0]);
            }
        }
    }

    public static void cadastro(String[][] matriz, int linhas, int posicao) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("NOME: ");
        matriz[posicao][0] = scanner.next();

        boolean idValido = false;
        String novoId = "";

        while (!idValido) {
            System.out.println("ID: ");
            novoId = scanner.next();

            int aux = 0;
            for (int i = 0; i < linhas; i++) {
                if (matriz[i][1] != null && matriz[i][1].equals(novoId)) {
                    aux++;
                }
            }

            if (aux > 0) {
                System.out.println("ID já existente. Informe outro ID.");
            } else {
                idValido = true;
                matriz[posicao][1] = novoId;
            }
        }

        System.out.println("TURMA: [A/B]");
        matriz[posicao][2] = scanner.next();
        while (!matriz[posicao][2].equals("A") && !matriz[posicao][2].equals("B")) {
            System.out.println("Informe uma turma válida [A/B] ");
            matriz[posicao][2] = scanner.next();
        }
        System.out.println("Aluno " + matriz[posicao][0] + " cadastrado com sucesso!");
    }


    public static void lancarNotas(String[][] matriz, int linhas, String idAluno) {
        Scanner scanner = new Scanner(System.in);
        boolean lancarNotas = true;
        int aux = 0;
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][1] != null && matriz[i][1].equals(idAluno)) {
                aux = i;
                if (matriz[i][4] != null && !matriz[i][4].equals(" - ")) {
                    lancarNotas = false;
                    System.out.println("Notas do aluno " + matriz[i][0] + " já foram lançadas.");
                }
            }
        }

        while (lancarNotas) {
            System.out.println("Nota em Biologia: (De 0 a 100) ");
            matriz[aux][4] = scanner.nextLine();
            while (Integer.parseInt(matriz[aux][4]) < 0 || Integer.parseInt(matriz[aux][4]) > 100) {
                System.out.println("Nota inválida. Informe novamente: ");
                matriz[aux][4] = scanner.nextLine();
            }

            System.out.println("Nota em Matemática: (De 0 a 100) ");
            matriz[aux][5] = scanner.nextLine();
            while (Integer.parseInt(matriz[aux][5]) < 0 || Integer.parseInt(matriz[aux][5]) > 100) {
                System.out.println("Nota inválida. Informe novamente: ");
                matriz[aux][5] = scanner.nextLine();
            }

            System.out.println("Nota em Português: (De 0 a 100) ");
            matriz[aux][6] = scanner.nextLine();
            while (Integer.parseInt(matriz[aux][6]) < 0 || Integer.parseInt(matriz[aux][6]) > 100) {
                System.out.println("Nota inválida. Informe novamente: ");
                matriz[aux][6] = scanner.nextLine();
            }
            System.out.println("As notas do aluno " + matriz[aux][0] + " foram lançadas com sucesso!");
            lancarNotas = false;
        }
    }

    public static float calculoMediaGeral(String[][] matriz, int linhas, int disciplina) {
        int numeroNotas = 0;
        int soma = 0;
        Float resultado = (float) 0;

        for (int i = 0; i < linhas; i++) {
            if (disciplina == 1) {
                if (matriz[i][4] != null && !matriz[i][4].equals(" - ")) {
                    int valor = Integer.parseInt(matriz[i][4]);
                    soma += valor;
                    numeroNotas++;
                }
            }
            if (disciplina == 2) {
                if (matriz[i][5] != null && !matriz[i][5].equals(" - ")) {
                    int valor = Integer.parseInt(matriz[i][5]);
                    soma += valor;
                    numeroNotas++;
                }
            }
            if (disciplina == 3) {
                if (matriz[i][6] != null && !matriz[i][6].equals(" - ")) {
                    int valor = Integer.parseInt(matriz[i][6]);
                    soma += valor;
                    numeroNotas++;
                }
            }
        }
        if (numeroNotas == 0) {
            return 0;
        } else {
            return soma / numeroNotas;
        }
    }

    public static Float calculoMediaAluno(String[][] matriz, int linhas, String id) {
        Float resultado = (float) 0;
        Float soma = (float) 0;
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][1] != null && matriz[i][1].equals(id)) {
                for (int j = 4; j < 7; j++) {
                    if (matriz[i][j] == null || matriz[i][j].equals(" - ")) {
                        resultado = (float) -1;
                        break;
                    } else if (matriz[i][j] != null) {
                        soma += Float.parseFloat(matriz[i][j]);
                    }
                }
                break;
            }
        }
        if (resultado == -1) {
            return resultado;
        } else {
            return soma / 3;
        }
    }

    public static void chamada(String[][] matriz, int linhas, String turma) {
        Scanner scanner = new Scanner(System.in);

        if (turma.equals("A")) {
            for (int i = 0; i < linhas; i++) {
                if (matriz[i][2] != null && matriz[i][2].equals("A")) {
                    System.out.println(matriz[i][0] + ": Presença ou falta [P/F] ");
                    String chamada = scanner.next();
                    while (!chamada.equals("P") && !chamada.equals("F")) {
                        System.out.println("Presença ou Falta [P/F] ");
                        chamada = scanner.next();
                    }
                    if (chamada.equals("F")) {
                        if (matriz[i][3] == null || matriz[i][3].equals(" - ")) {
                            matriz[i][3] = "1";
                        } else {
                            int numero = Integer.parseInt(matriz[i][3]);
                            numero += 1;
                            matriz[i][3] = Integer.toString(numero);
                        }
                    }
                }
            }
        }

        if (turma.equals("B")) {
            for (int i = 0; i < linhas; i++) {
                if (matriz[i][2] != null && matriz[i][2].equals("B")) {
                    System.out.println(matriz[i][0] + ": Presença ou falta [P/F] ");
                    String chamada = scanner.next();
                    while (!chamada.equals("P") && !chamada.equals("F")) {
                        System.out.println("Presença ou Falta [P/F] ");
                        chamada = scanner.next();
                    }
                    if (chamada.equals("F")) {
                        if (matriz[i][3] == null || matriz[i][3].equals(" - ")) {
                            matriz[i][3] = "1";
                        } else {
                            int numero = Integer.parseInt(matriz[i][3]);
                            numero += 1;
                            matriz[i][3] = Integer.toString(numero);
                        }
                    }
                }
            }
        }
    }

    public static void remover(String[][] matriz, int linhas, String id) {
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][1] != null && matriz[i][1].equals(id)) {
                System.out.println("Aluno " + matriz[i][0] + " encontrado e removido com sucesso!");
                for (int j = 0; j < 7; j++) {
                    matriz[i][j] = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos alunos serão cadastrados? ");
        int numeroAlunos = scanner.nextInt();
        while (numeroAlunos <= 0) {
            System.out.println("Valor inválido. Informe um número positivo e inteiro:");
            numeroAlunos = scanner.nextInt();
        }
        String[][] listaAlunos = new String[numeroAlunos][7];

        int opcao = 1;
        while (opcao != 0) {
            for (int i = 0; i < 20; i++) {
                System.out.print("=-");
            }
            System.out.println(" ");
            System.out.println("[ 0 ] Sair");
            System.out.println("[ 1 ] Matricular novo aluno");
            System.out.println("[ 2 ] Exibir tabela completa");
            System.out.println("[ 3 ] Exibir nome dos alunos + Id");
            System.out.println("[ 4 ] Lançar notas");
            System.out.println("[ 5 ] Exibir Média geral dos alunos por disciplina");
            System.out.println("[ 6 ] Exibir média das três disciplinas de um aluno");
            System.out.println("[ 7 ] Fazer chamada");
            System.out.println("[ 8 ] Remover cadastro de um aluno");
            for (int i = 0; i < 20; i++) {
                System.out.print("=-");
            }
            System.out.println(" ");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            while (opcao < 0 || opcao > 9) {
                System.out.println("Opção inexistente. Escolha uma opção válida: ");
                opcao = scanner.nextInt();
            }

            switch (opcao) {
                case 1:
                    System.out.print("As opçoes de posição para a tabela é ");
                    for (int i = 1; i <= numeroAlunos; i++) {
                        System.out.print(i + " - ");
                    }
                    System.out.println("");

                    System.out.println("Em qual posição você deseja inserir o novo aluno? ");
                    int posicaoNovoAluno = scanner.nextInt();
                    while (posicaoNovoAluno < 1 || posicaoNovoAluno > numeroAlunos) {
                        System.out.println("Posição inválida. Informe outra posição:");
                        posicaoNovoAluno = scanner.nextInt();
                    }

                    if (listaAlunos[posicaoNovoAluno - 1][0] != null && !listaAlunos[posicaoNovoAluno - 1][0].equals(" - ")) {
                        System.out.println("Posição já ocupada. Caso queira adicionar um aluno nesse posição, remova o aluno atual ou tente em outra posição.");
                    } else {
                        posicaoNovoAluno -= 1;
                        cadastro(listaAlunos, numeroAlunos, posicaoNovoAluno);
                    }
                    break;

                case 2:
                    exibirLista(listaAlunos, numeroAlunos);
                    break;

                case 3:
                    exibirNomes(listaAlunos, numeroAlunos);
                    break;

                case 4:
                    Boolean valido = false;
                    Boolean fazer = true;
                    String idAluno = "";
                    while (!valido) {
                        System.out.println("Informe um Id válido do aluno para lançar as notas: (-1 para voltar) ");
                        idAluno = scanner.next();
                        for (int i = 0; i < numeroAlunos; i++) {
                            if (listaAlunos[i][1] != null && listaAlunos[i][1].equals(idAluno)) {
                                valido = true;

                            } else if (idAluno.equals("-1")) {
                                fazer = false;
                                break;
                            }
                        }
                        if (!fazer) {
                            break;
                        }
                    }
                    if (fazer) {
                        lancarNotas(listaAlunos, numeroAlunos, idAluno);
                    }
                    break;

                case 5:
                    System.out.println("Qual disciplina você deseja ver a média geral?");
                    System.out.println("[ 1 ] Biologia");
                    System.out.println("[ 2 ] Matemática");
                    System.out.println("[ 3 ] Português");
                    int numeroDisciplina = scanner.nextInt();
                    while (numeroDisciplina < 1 || numeroDisciplina > 3) {
                        System.out.println("Opção inválida. Tente novamente.");
                        numeroDisciplina = scanner.nextInt();
                    }

                    String disciplina = "";
                    switch (numeroDisciplina) {
                        case 1:
                            disciplina = "Biologia";
                            break;
                        case 2:
                            disciplina = "Matemática";
                            break;
                        case 3:
                            disciplina = "Português";
                            break;
                    }

                    Float resultado = calculoMediaGeral(listaAlunos, numeroAlunos, numeroDisciplina);
                    if (resultado == 0) {
                        System.out.println("Nenhuma nota encontrada.");
                    } else {
                        System.out.println("A nota média de todos os alunos na matéria de " + disciplina + " é " + resultado + " pontos.");
                    }
                    break;

                case 6:
                    Boolean idValido = false;
                    Boolean fazerMedia = true;
                    String idAluno2 = "";
                    int aux = 0;
                    while (!idValido) {
                        System.out.println("Informe um Id válido do aluno para visualizar a média das três disciplinas: (-1 para voltar) ");
                        idAluno2 = scanner.next();
                        for (int i = 0; i < numeroAlunos; i++) {
                            if (listaAlunos[i][1] != null && listaAlunos[i][1].equals(idAluno2)) {
                                idValido = true;
                                aux = i;

                            } else if (idAluno2.equals("-1")) {
                                fazerMedia = false;
                                break;
                            }
                        }
                        if (!fazerMedia) {
                            break;
                        }
                    }

                    Float res = calculoMediaAluno(listaAlunos, numeroAlunos, idAluno2);

                    if (fazerMedia) {
                        if (res == -1) {
                            System.out.println("As notas do aluno " + listaAlunos[aux][0] + " ainda não foram lançadas.");
                        } else {
                            System.out.println("A média do aluno " + listaAlunos[aux][0] + " é " + res + " pontos.");
                        }
                    }
                    break;

                case 7:
                    System.out.println("Fazer chamada de qual turma? [A/B] ");
                    String turmaChamada = scanner.next();
                    while (!turmaChamada.equals("A") && !turmaChamada.equals("B")) {
                        System.out.println("Informe uma turma válida [A/B]");
                        turmaChamada = scanner.next();
                    }

                    chamada(listaAlunos, numeroAlunos, turmaChamada);
                    break;

                case 8:
                    Boolean idRemoverValido = false;
                    Boolean removerAluno = true;
                    String idRemover = "";
                    int x = 0;
                    int numeroNull = 0;

                    while (!idRemoverValido) {
                        for (int i = 0; i < numeroAlunos; i++) {
                            if (listaAlunos[i][1] == null || listaAlunos[i][1].equals(" - ")) {
                                numeroNull += 1;
                            }
                        }

                        if (numeroNull == numeroAlunos) {
                            removerAluno = false;
                            System.out.println("Nenhum aluno para remover.");
                            break;
                        }
                        System.out.println("Informe um Id válido do aluno para removê-lo: (-1 para voltar) ");
                        idRemover = scanner.next();
                        for (int i = 0; i < numeroAlunos; i++) {
                            if (listaAlunos[i][1] != null && listaAlunos[i][1].equals(idRemover)) {
                                idRemoverValido = true;
                                aux = i;

                            } else if (idRemover.equals("-1")) {
                                removerAluno = false;
                                break;
                            }
                        }
                        if (!removerAluno) {
                            break;
                        }
                    }
                    if (removerAluno) {
                        remover(listaAlunos, numeroAlunos, idRemover);
                    }

                    break;
            }
        }
    }
}
