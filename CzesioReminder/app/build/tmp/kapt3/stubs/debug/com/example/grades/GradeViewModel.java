package com.example.grades;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u0007J\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u0007J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/grades/GradeViewModel;", "Landroidx/lifecycle/ViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_grade", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/grades/Grade;", "_gradesState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "grade", "Landroidx/lifecycle/LiveData;", "getGrade", "()Landroidx/lifecycle/LiveData;", "gradesState", "Lkotlinx/coroutines/flow/StateFlow;", "getGradesState", "()Lkotlinx/coroutines/flow/StateFlow;", "repository", "Lcom/example/grades/GradesRepository;", "addGrade", "", "clearGrade", "delete", "fetchGrades", "getGradeById", "id", "", "update", "app_debug"})
public final class GradeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.grades.GradesRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.grades.Grade>> _gradesState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.grades.Grade> _grade = null;
    
    public GradeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.grades.Grade>> getGradesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.grades.Grade> getGrade() {
        return null;
    }
    
    private final void fetchGrades() {
    }
    
    public final void clearGrade() {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull()
    com.example.grades.Grade grade) {
    }
    
    public final void addGrade(@org.jetbrains.annotations.NotNull()
    com.example.grades.Grade grade) {
    }
    
    public final void getGradeById(int id) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    com.example.grades.Grade grade) {
    }
}