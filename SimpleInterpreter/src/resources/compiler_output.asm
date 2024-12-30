section .data
a dd 1
b dd 1
c dd 1
d dd 1
e dd 1
section .text
global _start
_start:
; Allocating a eax for  a
mov eax, 5
; Updating the new value of a for their register eax
mov eax, 6
; Allocating ebx register for b
mov ebx, eax
; Allocating ecx register for d
mov ecx, eax
mov ebx, ecx
; Having to fetch from memory
mov edx, [e]
mov ebx, edx
; Allocating a register for c would have taken up the register designated for a : eax
mov dword [c], eax
mov ecx, ebx
mov ebx, eax
mov dword [a], eax
mov dword [b], ebx
mov dword [d], ecx
mov dword [e], edx
mov rax, 1
xor rbx, rbx
int 0x80
