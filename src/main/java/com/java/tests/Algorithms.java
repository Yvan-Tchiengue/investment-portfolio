package com.java.tests;

import java.util.HashMap;
import java.util.Map;

public class Algorithms {
	
	/**
	 * Trouver le deuxième plus grand élément dans un tableau

       L'idée est de parcourir le tableau une fois pour trouver le plus grand élément, 
       puis une seconde fois pour trouver le deuxième plus grand.
       on parcourt une seule fois le tableau O(n)
	 */
	public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Le tableau doit contenir au moins deux éléments");
        }

        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        
        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num < first) {
                second = num;
            }
        }

        return (second == Integer.MIN_VALUE) ? -1 : second; // -1 si pas de deuxième plus grand
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 8, 20, 15};
        System.out.println("Deuxième plus grand élément: " + findSecondLargest(arr)); // Output: 15
    }
    
    
    
    /**
     * Tri d’un tableau avec QuickSort

       QuickSort est un algorithme de tri efficace qui utilise le divide and conquer.
       tri en =(nlogn) dans le meilleur cas
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; 
        int i = low - 1; 

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Recherche dichotomique (Binary Search)

       Cet algorithme recherche un élément dans un tableau trié en divisant 
       l’espace de recherche en deux à chaque étape.
       recherche en O(log n)
     * 
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Élement non trouvé
    }

    
    /**
     * Vérifier si une chaîne est un palindrome

       📌 Cette méthode vérifie si une chaîne est un palindrome 
       (c'est-à-dire si elle se lit de la même manière dans les deux sens). 
       Elle utilise deux pointeurs pour comparer les caractères aux extrémités.
     * O(n)
     */
    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    
    /**
     * Compresser une chaîne de caractères

       📌 Cette méthode compresse une chaîne en comptant les répétitions consécutives 
       de chaque caractère.
       Exemple : "aaabbcddd" devient "a3b2c1d3".
       Si la chaîne compressée est plus longue que l’originale, elle retourne la chaîne 
       d'origine.
     * 
     * O(n)
     */
    public static String compressString(String str) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        compressed.append(str.charAt(str.length() - 1)).append(count);

        return compressed.length() < str.length() ? compressed.toString() : str;
    }
    
    
    
    /**
     * Trouver le plus long sous-mot sans caractères répétitifs

       📌 Cette méthode trouve la longueur du plus long sous-mot (sous-chaîne) 
       sans caractères répétés.
       Elle utilise une technique de fenêtre glissante (Sliding Window) avec HashMap 
       pour suivre les caractères et leur dernière position.
       O(n)
     * @param str
     * @return
     */
    public static int longestUniqueSubstring(String str) {
        int maxLength = 0;
        Map<Character, Integer> seen = new HashMap<>();
        int left = 0;

        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            if (seen.containsKey(c)) {
                left = Math.max(seen.get(c) + 1, left);
            }
            seen.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }



}

